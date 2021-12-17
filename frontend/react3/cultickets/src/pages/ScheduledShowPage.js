import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink, useParams } from "react-router-dom";

export const ScheduledShowPage = () => {
  const showId = useParams().id;
  const [scheduledShows, setScheduledShows] = useState();
  const { token } = useContext(AuthContext);
  const { request } = useHttp();

  const fetchShows = useCallback(async () => {
    try {
      const fetched = await request(`/shows/showId=${showId}`, "GET", null, {
        Authorization: `Bearer ${token}`,
      });
      console.log("Show schedule", fetched);

      setScheduledShows(fetched);
    } catch (e) {}
  }, [showId, setScheduledShows]);

  useEffect(() => {
    fetchShows();
  }, [fetchShows]);
  if (scheduledShows) {
    console.log("Show", scheduledShows.show);
    const dates = scheduledShows.scheduled_shows.map((e) => {
      return (
        <>
          <NavLink className="btn btn-warning mt-3" to={`/tickets/${e.id}`}>
            Date: {e.datetime}
          </NavLink>
          <br />
        </>
      );
    });

    return (
      <>
        <div key={scheduledShows.show.id} className="card">
          <div className="card-header">
            <div className="container">
              <div className="row">
                <span className="col">{scheduledShows.show.name}</span>
              </div>
            </div>
          </div>
        </div>
        <div className="card-body">
          <p className="card-text">{scheduledShows.show.description}</p>
          {dates}
        </div>
      </>
    );
  }
  return <Loader></Loader>;
  //   return showList;
};
