import { createRequest, createRequestWithBody } from "./utils";
import { userController } from "./userController";

const $ = require("jquery");

function displayUserTickets(data, status) {
  const ticketList = $("#tickets");
  ticketList.empty();
  data.tickets.forEach((e) => {
    ticketList.append(`
        <div class="card">
        <div class="card-header">
        <div class="container">
          <div class="row">
            <span class="col">
            ${e.show.name}
            </span>
            <span class="col text-secondary">
            ${e.show.theater.name}
            </span>
            </div>
          </div>
          </div>
        </div>
        <div class="card-body">
          <ul>
            <li>Row: ${e.seat.row}</li>
            <li>Number: ${e.seat.number}</li>
            <li>Date: ${e.show.datetime}</li>
            <li>Price: ${e.seat.price.price} $</li>
          </ul>
          <button value="${e.id}" class="btn btn-warning return">Return</button>
        </div>
      </div>`);
  });
}

function logOut() {
  localStorage.setItem("currentUser", "");
  localStorage.setItem("token", "");
  window.location = "shows.html";
}

function returnTicket(ticket) {
  createRequestWithBody(
    "/user/tickets/return/",
    "PUT",
    ticket,
    getAllUserTickets
  );
}

const user = JSON.parse(localStorage.getItem("currentUser"));
console.log("User", user);
$("#user-name").append(user.userName);
$("#user-firstName").append(user.firstName);
$("#user-email").append(user.email);

function getAllUserTickets() {
  createRequest("/user/tickets", "get", (data, status) => {
    displayUserTickets(data, status);
    userController.tickets = data.tickets;
  });
}

getAllUserTickets();
$(document).on("click", ".btn.btn-warning.return", function () {
  const id = $(this).val();
  const ticket = userController.getTicket(id);
  console.log(ticket);
  returnTicket(ticket);
});

$(document).on("click", "#sign-out", () => logOut());
