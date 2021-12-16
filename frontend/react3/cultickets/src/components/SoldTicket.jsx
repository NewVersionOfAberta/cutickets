import React, { useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const SoldTicket = ({children}) => {
    const ticket = children;
    const { token, userId } = useContext(AuthContext);
    const { loading, request } = useHttp();
    const id = ticket.id;
    const returnHandler = useCallback(async () => {
        try {
          const data = await request(
            `/user/tickets/return/user=${userId}&ticket=${id}`,
            "Get",
            null,
            { Authorization: `user ${token}` }
          );
        } catch (e) {}
      }, [request, token, id, userId]);
    const returnButton = ticket.ticketStatus.name == "sold" ? <button onClick={() => returnHandler(id)}>Return</button> : <></>;
    // public class TicketDto {
//   private int id;
//   private int price;
//   private SeatDto seat;
//   private TheaterHall theaterHall;
//   private Sector sector;
//   private Theatre theatre;
//   private ShowDto show;
//   private TicketStatusDto ticketStatus;
// }
    return <>
        <div className="card">
        <div className="card-header">
        <div className="container">
          <div className="row">
            <span className="col text-secondary">
            {ticket.showName}
            </span>
            </div>
          </div>
          </div>
        </div>
        <div className="card-body">
          <ul>
            <li>Row: {ticket.seat.row}</li>
            <li>Number: {ticket.seat.number}</li>
            <li>Status: {ticket.ticketStatus.name}</li>
            {/* <li>Date: {ticket.show.datetime}</li> */}
            <li>Price: {ticket.price / 100} $</li>
          </ul>
          {returnButton}
        </div>
  </>
}