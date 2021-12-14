import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink } from "react-router-dom";

export const ShowPage = () => {
  const [shows, setShows] = useState([]);
  const { token, userName } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchShows = useCallback(async () => {
    try {
      const fetched = await request("/shows/theater", "GET", null, {
        Authorization: `user ${token}`,
      });
      setShows(fetched.shows);
    } catch (e) {}
  }, [request, token, setShows]);

  useEffect(() => {
    fetchShows();
  }, [fetchShows]);

  // const deadlineContext = { deadlines: deadlines, fetch: fetchDeadlines };

  // if (loading) {
  //     return <Loader />;
  // }
  return shows.map((e) => (
    <>
      <div class="card">
        <div class="card-header">
          <div class="container">
            <div class="row">
              <span class="col">{e.name}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="card-body">
        <p class="card-text">{e.description}</p>
        <NavLink to={`/show/${e.id}`}>Book</NavLink>
      </div>
    </>
  ));
  //   return showList;
};
