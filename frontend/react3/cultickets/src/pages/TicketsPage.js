import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Link, useParams } from "react-router-dom";
import { Seat } from "../components/Seat";

export const TicketsPage = () => {
  const showId = useParams().id;
  const [tickets, setTickets] = useState([]);
  const [change, setChange] = useState(false);
  //   const [rows, setRows] = useState(0);
  //   const [places, setPlaces] = useState(0);

  const { token, userName } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchTickets = useCallback(async () => {
    try {
      const fetched = await request(
        `/tickets/scheduledShow/${showId}`,
        "GET",
        null,
        {
          Authorization: `user ${token}`,
        }
      );
      setChange(false);
      setTickets(fetched.tickets);
      console.log(fetched.tickets);
    } catch (e) {}
  }, [request, token, showId, setTickets, change]);

  useEffect(() => {
    fetchTickets();
  }, [fetchTickets]);

  return tickets.map((e) => {
    return <Seat>{{ ticket: e, setChange }}</Seat>;
  });
};
