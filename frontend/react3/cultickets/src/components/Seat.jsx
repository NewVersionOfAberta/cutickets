import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const Seat = ({children}) => {
    const ticket = children;
    const { token, userName } = useContext(AuthContext);
    const { loading, request } = useHttp();
    const id = ticket.id;
    const buyHandler = useCallback(async () => {
        try {
          const data = await request(
            `tickets/buy/${id}`,
            "DELETE",
            null,
            { Authorization: `user ${token}` }
          );
        } catch (e) {}
      }, [request, token, id]);

    return <>
        <div className="card">
        <div className="card-header">
        <div className="container">
          <div className="row">
            <span className="col text-secondary">
            {ticket.theatre.name}
            </span>
            </div>
          </div>
          </div>
        </div>
        <div className="card-body">
          <ul>
            <li>Row: {ticket.seat.row}</li>
            <li>Number: {ticket.seat.number}</li>
            <li>Sector: {ticket.sector.name}</li>
            <li>Hall: {ticket.theaterHall.name}</li>
            <li>Price: {ticket.price / 100} $</li>
          </ul>
          <button value="${e.id}" className="btn btn-warning book">Buy</button>
        </div>
  </>
}