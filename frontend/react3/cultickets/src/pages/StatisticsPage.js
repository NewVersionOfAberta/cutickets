import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { LineChart, Line, XAxis, YAxis } from "recharts";

export const StatisticsPage = () => {
  const [genre, setGenre] = useState([]);
  const [stats, setStats] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();
  const fetchShows = useCallback(async () => {
    try {
      const fetched = await request(`/user/${userId}/stats`, "GET", null, {
        Authorization: `Bearer ${token}`,
      });
      setGenre(fetched.info);
      let exp = fetched.exps;

      exp.sort((a, b) => {
        if (parseInt(a.month) > parseInt(b.month)) {
          return 1;
        }
        if (parseInt(a.month) < parseInt(b.month)) {
          return -1;
        }
        return 0;
      });
      console.log("sorted", exp);
      setStats(exp);
      console.log("Stats", genre, stats);
    } catch (e) {}
  }, [request, token, setGenre, setStats, userId]);

  useEffect(() => {
    fetchShows();
  }, [fetchShows]);
  if (!stats || stats.length == 0) {
    return <div> No information about this user</div>;
  }
  const data = stats.map((e) => {
    return { name: e.month, uv: e.price / 100 };
  });
  const genresString = genre.map((e) => e.name + " ");
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
      <h5 className="text-success">The most popular genres: {genresString}</h5>
    </div>
  );
};
