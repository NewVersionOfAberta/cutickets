import { ticketController } from "./ticketController";
import { createRequest } from "./utils";

const $ = require("jquery");

const currentShow = JSON.parse(localStorage.getItem("currentShow"));
const showScheme = currentShow.scheme;

const displayAvaliableSeats = (data, status) => {
  const tickets = data.tickets;
  ticketController.tickets = tickets;
  tickets.forEach((e) => {
    const curItem = $(`#r${e.seat.row}c${e.seat.number}`);
    curItem.css("background-color", e.seat.price.color);
  });
};

$("#show-name").append(currentShow.name);
$("#theater-name").append(currentShow.theater.name);
$("#show-time").append(currentShow.datetime);
$("#show-type").append(currentShow.showType.name);
$("#show-description").append(currentShow.description);

let visualScheme = "";
for (let i = 1; i < showScheme.rowsAmount + 1; i++) {
  visualScheme += `<div class="row w-50" >`;
  for (let j = 1; j < showScheme.maxSeatsAmount + 1; j++) {
    visualScheme += `<div class="col m-2 ticket" id="r${i}c${j}" style="background-color: #bababa; height: 50px;">${j}</div>`;
  }
  visualScheme += `</div>`;
}
const theaterScheme = $("#theater-hall");
theaterScheme.append(visualScheme);

createRequest("/tickets/show/" + currentShow.id, "get", displayAvaliableSeats);

$(document).on("click", ".col.m-2.ticket", function () {
  const id = this.id;
  const currentTicket = $(this);
  const rowColumn = id.split(/\D+/).filter(Boolean);
  console.log(rowColumn);
  ticketController.chooseTicket(rowColumn[0], rowColumn[1], currentTicket);
  ticketController.displayTickets();
});

$(document).on("click", ".btn.btn-warning.book", function () {
  const id = $(this).val();
  ticketController.deleteTicket(id);
  ticketController.displayTickets();
});

$(document).on("click", "#btn-buy", function () {
  ticketController.buyAllTickets();
});
