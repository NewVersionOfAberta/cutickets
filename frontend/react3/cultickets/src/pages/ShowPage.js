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
  console.log("Shows", shows);
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
        <h5 className="card-text">{e.description}</h5>
        <p className="card-text">{`Theatre: ${e.theatre.name}.`}</p>
        <h6 className="text-success">{`${e.ageRating.name}`}</h6>
        <h6 className="text-success">{`Genres: ${e.genre.map(
          (e) => " " + e.name
        )}`}</h6>
        <NavLink
          className="btn btn-warning book"
          to={`/scheduled-show/${e.id}`}
        >
          Book
        </NavLink>
      </div>
    </>
  ));
  //   return showList;
};
