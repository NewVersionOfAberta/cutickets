import React, { useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const SoldTicket = ({children}) => {
    const ticket = children;
    const { token, userName } = useContext(AuthContext);
    const { loading, request } = useHttp();
    const id = ticket.id;
    const returnHandler = useCallback(async () => {
        try {
          const data = await request(
            `tickets/return/${id}`,
            "DELETE",
            null,
            { Authorization: `user ${token}` }
          );
        } catch (e) {}
      }, [request, token, id]);
    const returnButton = <button onClick={returnHandler}>Return</button>;
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
            <li>Row: {ticket.row}</li>
            <li>Number: {ticket.place}</li>
            <li>Status: {ticket.status}</li>
            <li>Date: {ticket.datetime}</li>
            <li>Price: {ticket.price / 100} $</li>
          </ul>
          <button value="${e.id}" className="btn btn-warning book">Return</button>
        </div>
  </>
}