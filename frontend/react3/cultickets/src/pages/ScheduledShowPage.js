import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink } from "react-router-dom";

export const ScheduledShowPage = () => {
  const shows1 = {
    show: {
      name: "Allo",
      description: "Hi",
      rating: "18+",
      genres: ["Romantic", "Comedy"],
    },
    scheduledShows: [
      { id: 1, date: "13:00 2022-11-10" },
      { id: 1, date: "14:40 2021-12-12" },
    ],
  };
  const [shows, setShows] = useState(shows1);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  //   const fetchShows = useCallback(async () => {
  //     // try {
  //     //   const fetched = await request(
  //     //     `/shows/userId=${userId}&theatreId=0&genreId=0`,
  //     //     "GET",
  //     //     null,
  //     //     {
  //     //       Authorization: `user ${token}`,
  //     //     }
  //     //   );
  //     console.log("Show1");

  //     setShows(shows1);
  //     console.log("Show1", shows1);
  //     // } catch (e) {}
  //   }, [setShows]);

  //   useEffect(() => {
  //     fetchShows();
  //   }, [fetchShows]);

  console.log(shows);
  const Dates = shows.scheduledShows.map((e) => {
    <NavLink to={`/tickets/${e.id}`}>{e.date}</NavLink>;
  });

  return shows.map((e) => (
    <>
      <div key={e.id} className="card">
        <div className="card-header">
          <div className="container">
            <div className="row">
              <span className="col">{e.name}</span>
            </div>
          </div>
        </div>
      </div>
      <div className="card-body">
        <p className="card-text">{e.description}</p>
        <Dates />
      </div>
    </>
  ));
  //   return showList;
};
