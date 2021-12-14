import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Link, useParams } from "react-router-dom";
import { Seat } from "../components/Seat";

export const TicketsPage = () => {
  const showId = useParams().id;
  const [tickets, setTickets] = useState([]);
  //   const [rows, setRows] = useState(0);
  //   const [places, setPlaces] = useState(0);

  const { token, userName } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchTickets = useCallback(async () => {
    try {
      const fetched = await request(`/tickets/show/${showId}`, "GET", null, {
        Authorization: `user ${token}`,
      });
      setTickets(fetched.tickets);
      //   setRows(fetched.rows);
      //   setPlaces(fetched.places);
    } catch (e) {}
  }, [request, token, showId, setTickets]);

  useEffect(() => {
    fetchTickets();
  }, [fetchTickets]);

  return tickets.map((e) => {
    <Seat>{{ id: e.id, row: e.row, column: e.place, price: e.price }}</Seat>;
  });
};
