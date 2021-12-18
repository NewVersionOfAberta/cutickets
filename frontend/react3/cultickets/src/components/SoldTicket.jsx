import React, { useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const SoldTicket = ({children}) => {
    const { ticket, setChange } = children;
    const { token, userId } = useContext(AuthContext);
    const { loading, request } = useHttp();
    const id = ticket.id;
    const returnHandler = useCallback(async () => {
        try {
          const data = await request(
            `/user/tickets/return/user=${userId}&ticket=${id}`,
            "Get",
            null,
            { Authorization: `Bearer ${token}` }
          );
          setChange(true);
        } catch (e) {}
      }, [request, token, id, userId, setChange]);
    const returnButton = ticket.ticketStatus.name == "sold" ? <button className="btn btn-warning book" onClick={() => returnHandler(id)}>Return</button> : <></>;
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
      console.log("Ticket", ticket);
    return <>
        <div className="card">
        <div className="card-header">
        <div className="container">
          <div className="row">
            <span className="col text-secondary">
            {ticket.show.name}
            </span>
            </div>
          </div>
          </div>
        </div>
        <div className="card-body">
          <ul>
            <li>Theatre: {ticket.theatre.name}</li>
            <li>Hall: {ticket.theaterHall.name}</li>
            <li>Sector: {ticket.sector.name}</li>
            <li>Row: {ticket.seat.row}</li>
            <li>Number: {ticket.seat.number}</li>
            <li>Status: {ticket.ticketStatus.name}</li>
            
            <li>Date: {ticket.show.datetime}</li>
            <li>Price: {ticket.price / 100} $</li>
          </ul>
          {returnButton}
        </div>
  </>
}