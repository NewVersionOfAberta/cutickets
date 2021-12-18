import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";

export const JornalPage = () => {
  const [jornal, setJornal] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchJornal = useCallback(async () => {
    try {
      const fetched = await request(`/admin/jornal`, "GET", null, {
        Authorization: `Bearer ${token}`,
      });
      setJornal(fetched.jornal);
    } catch (e) {}
  }, [request, token, setJornal]);

  useEffect(() => {
    fetchJornal();
  }, [fetchJornal]);

  return jornal && jornal.length ? (
    <table>
      <tr>
        <td>Login</td>
        <td>Action</td>
        <td>Table</td>
        <td>Value</td>
        <td>Column</td>
        <td>Time</td>
      </tr>

      {jornal.map((e) => (
        <tr>
          <td>{e.login}</td>
          <td>{e.action}</td>
          <td>{e.table}</td>
          <td>{e.value}</td>
          <td>{e.column}</td>
          <td>{e.time}</td>
        </tr>
      ))}
    </table>
  ) : (
    <div>Operation jornal is empty</div>
  );
};
