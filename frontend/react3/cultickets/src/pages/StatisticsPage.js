import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { LineChart, Line, XAxis, YAxis } from "recharts";

export const StatisticsPage = () => {
  const [shows, setShows] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();
  const data = [
    { name: "January", uv: 40 },
    { name: "February", uv: 0 },
    { name: "March", uv: 0 },
    { name: "April", uv: 20 },
    { name: "May", uv: 15 },
    { name: "June", uv: 0 },
    { name: "July", uv: 0 },
    { name: "August", uv: 0 },
    { name: "September", uv: 40 },
    { name: "October", uv: 0 },
    { name: "November", uv: 0 },
    { name: "December", uv: 0 },
  ];

  return (
    <div className="card">
      <div class="card-body">
        <h5 class="card-title">Money spend on tickets in 2021</h5>
        <p class="card-text">Amazing!</p>
      </div>
      <h3></h3>
      <LineChart width={1000} height={500} data={data}>
        <Line type="monotone" dataKey="uv" stroke="#8884d8" />
        <XAxis dataKey="name" />
        <YAxis />
      </LineChart>
      <h5 className="text-success">
        The most popular genres: Romantic, Comedy.
      </h5>
    </div>
  );
  //   const fetchShows = useCallback(async () => {
  //     try {
  //       const fetched = await request(
  //         `/shows/userId=${userId}&theatreId=0&genreId=0`,
  //         "GET",
  //         null,
  //         {
  //           Authorization: `user ${token}`,
  //         }
  //       );
  //       setShows(fetched.shows);
  //     } catch (e) {}
  //   }, [request, token, setShows, userId]);

  //   useEffect(() => {
  //     fetchShows();
  //   }, [fetchShows]);
};
