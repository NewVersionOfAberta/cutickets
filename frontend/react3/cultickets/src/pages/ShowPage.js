import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink } from "react-router-dom";

export const ShowPage = () => {
  const [shows, setShows] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchShows = useCallback(async () => {
    try {
      const fetched = await request(
        `/shows/userId=${userId}&theatreId=0&genreId=0`,
        "GET",
        null,
        {
          Authorization: `user ${token}`,
        }
      );
      setShows(fetched.shows);
    } catch (e) {}
  }, [request, token, setShows, userId]);

  useEffect(() => {
    fetchShows();
  }, [fetchShows]);

  // const deadlineContext = { deadlines: deadlines, fetch: fetchDeadlines };

  // if (loading) {
  //     return <Loader />;
  // }
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
        <NavLink to={`/scheduled-show/${e.id}`}>Book</NavLink>
      </div>
    </>
  ));
  //   return showList;
};
