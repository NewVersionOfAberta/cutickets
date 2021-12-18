import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink } from "react-router-dom";
import { Checkbox } from "../components/Checkbox";

export const ShowPage = () => {
  const [shows, setShows] = useState([]);
  const [theatres, setTheatres] = useState([]);
  const [genres, setGenres] = useState([]);
  const [theatre, setTheatre] = useState({ id: 0, name: "" });
  const [genre, setGenre] = useState({ id: 0, name: "" });
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchAllTheatres = useCallback(async () => {
    try {
      const fetched = await request(`/shows/filters`, "GET", null, {
        Authorization: `Bearer ${token}`,
      });
      setTheatres(fetched.theatres);
      setGenres(fetched.genres);
    } catch (e) {}
  }, [request, token, setTheatres]);

  const fetchAllShows = useCallback(async () => {
    try {
      const fetched = await request(
        `/shows/userId=0&theatreId=0&genreId=0`,
        "GET",
        null,
        {
          Authorization: `Bearer ${token}`,
        }
      );
      setShows(fetched.shows);
    } catch (e) {}
  }, [request, token, setShows]);

  const fetchSuitableShows = useCallback(async () => {
    try {
      console.log("Find suitable");
      const fetched = await request(
        `/shows/userId=${userId}&theatreId=0&genreId=0`,
        "GET",
        null,
        {
          Authorization: `Bearer ${token}`,
        }
      );
      setShows(fetched.shows);
    } catch (e) {}
  }, [request, token, setShows, userId]);

  const fetchTheatreShows = useCallback(
    async (e) => {
      try {
        const { name, value } = e.target;
        setTheatre({ id: value, name });
        const fetched = await request(
          `/shows/userId=0&theatreId=${value}&genreId=0`,
          "GET",
          null,
          {
            Authorization: `Bearer ${token}`,
          }
        );
        setShows(fetched.shows);
      } catch (e) {}
    },
    [request, token, setShows, userId]
  );

  const fetchGenreShows = useCallback(
    async (e) => {
      try {
        const { name, value } = e.target;
        console.log("Genre:", name, value);
        setGenre({ id: value, name });
        const fetched = await request(
          `/shows/userId=0&theatreId=0&genreId=${value}`,
          "GET",
          null,
          {
            Authorization: `Bearer ${token}`,
          }
        );
        setShows(fetched.shows);
      } catch (e) {}
    },
    [request, token, setShows, userId, setGenre]
  );

  useEffect(() => {
    fetchAllTheatres();
    fetchSuitableShows();
  }, [fetchSuitableShows, fetchAllTheatres]);

  let theatresList;
  if (theatres) {
    theatresList = theatres.map((e) => <option value={e.id}>{e.name}</option>);
  }
  let genresList;

  if (genres) {
    genresList = genres.map((e) => <option value={e.id}>{e.name}</option>);
  }

  let showsList;
  if (!shows) {
    showsList = <div>No shows avaliable</div>;
  } else {
    showsList = shows.map((e) => (
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
  }
  return (
    <>
      {theatresList ? (
        <select
          name="theatres"
          onChange={fetchTheatreShows}
          className="form-control"
          value={theatre.id}
        >
          {theatresList}
        </select>
      ) : (
        <></>
      )}
      {genresList ? (
        <select
          name="genres"
          onChange={fetchGenreShows}
          className="form-control"
          value={genre.id}
        >
          {genresList}
        </select>
      ) : (
        <></>
      )}
      <Checkbox>{{ fetchAllShows, fetchSuitableShows }}</Checkbox>
      {showsList}
    </>
  );
  //   return showList;
};
