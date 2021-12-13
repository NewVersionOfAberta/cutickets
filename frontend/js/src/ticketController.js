import { createRequestWithBody, moveToPage } from "./utils";

export const ticketController = {
  tickets: [],
  chosenTickets: [],
  displayTickets() {
    const ticketList = $("#tickets-to-buy");
    ticketList.empty();
    if (!this.chosenTickets.length) {
      return;
    }
    this.chosenTickets.forEach((e) => {
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
          <button value="${e.id}" class="btn btn-warning book">Cancel</button>
        </div>
      </div>`);
    });
  },

  chooseTicket(row, column, seat) {
    const ticket = this.tickets.find(
      (e) => e.seat.row == row && e.seat.number == column
    );
    console.log();
    if (ticket && $.inArray(ticket, this.chosenTickets) == -1) {
      this.chosenTickets.push(ticket);
      seat.css("background-color", "#E58479");
    }
  },

  deleteTicket(id) {
    this.chosenTickets = this.chosenTickets.filter((e) => {
      if (e.id == id) {
        $(`#r${e.seat.row}c${e.seat.number}`).css(
          "background-color",
          e.seat.price.color
        );
        return false;
      } else {
        return true;
      }
    });
  },

  buyAllTickets() {
    if (this.chosenTickets && this.chosenTickets.length > 0) {
      createRequestWithBody(
        "/user/tickets/purchase/",
        "PUT",
        this.chosenTickets,
        () => moveToPage("profile.html")
      );
    }
  },
};
