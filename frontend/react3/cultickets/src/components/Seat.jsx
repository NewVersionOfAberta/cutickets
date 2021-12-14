import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const Seat = ({id, row, column, style}) => {
    const { token, userName } = useContext(AuthContext);
    const { loading, request } = useHttp();

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
    <div class="card">
      <div class="card-header">
        <div class="container">
          <div class="row">
            <span class="col">{row}, {column}</span>
          </div>
        </div>
      </div>
    </div>
    <button  onClick={buyHandler}>Buy</button>
  </>
}