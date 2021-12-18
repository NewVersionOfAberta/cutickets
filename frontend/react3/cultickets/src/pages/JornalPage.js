import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const JornalPage = () => {
  const [jornal, setJornal] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchJornal = useCallback(async () => {
    try {
      const fetched = await request(`/admin/journal`, "GET", null, {
        Authorization: `Bearer ${token}`,
      });
      setJornal(fetched.journalInfo);
    } catch (e) {}
  }, [request, token, setJornal]);

  useEffect(() => {
    fetchJornal();
  }, [fetchJornal]);

  return jornal && jornal.length ? (
    <table className="table table-striped">
      <thead className="thead-dark">
        <tr>
          <th scope="col">Login</th>
          <th scope="col">Action</th>
          <th scope="col">Table</th>
          <th scope="col">Value</th>
          <th scope="col">Column</th>
          <th scope="col">Time</th>
        </tr>
      </thead>
      <tbody>
        {jornal.map((e) => (
          <tr>
            <td>{e.login}</td>
            <td>{e.operation}</td>
            <td>{e.table}</td>
            <td>{e.value}</td>
            <td>{e.column}</td>
            <td>{e.time}</td>
          </tr>
        ))}
      </tbody>
    </table>
  ) : (
    <div>Operation journal is empty</div>
  );
};
