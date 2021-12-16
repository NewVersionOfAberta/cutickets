import React, { useEffect, useState, useContext, useCallback } from "react";
import { useHttp } from "../hooks/http.hook";
import { AuthContext } from "../context/AuthContext";
import { Loader } from "../components/Loader";
import { NavLink } from "react-router-dom";

export const AdminPage = () => {
  const [users, setUsers] = useState([]);
  const { token, userId } = useContext(AuthContext);
  const { loading, request } = useHttp();

  const fetchShows = useCallback(async () => {
    try {
      const fetched = await request(`/admin/users`, "GET", null, {
        Authorization: `user ${token}`,
      });
      setUsers(fetched.users.sort((u1, u2) => u1.id - u2.id));
    } catch (e) {}
  }, [request, token, setUsers]);

  useEffect(() => {
    fetchShows();
  }, [fetchShows]);

  const activate = async (user) => {
    await request(`/admin/users/activate`, "POST", user, {
      Authorization: `user ${token}`,
    });
    fetchShows();
  };

  const disable = async (user) => {
    await request(`/admin/users/disable`, "POST", user, {
      Authorization: `user ${token}`,
    });
    fetchShows();
  };

  return users.map((e) => (
    <>
      <div key={e.id} className="card">
        <div className="card-header">
          <div className="container">
            <div className="row">
              <span className="col">{e.userName}</span>
            </div>
          </div>
        </div>
      </div>
      <div className="card-body">
        <p className="card-text">Name: {e.name}</p>
        <p className="card-text">Surname: {e.surname}</p>
        <p className="card-text">Email: {e.email}</p>
        <p className="card-text">Status: {e.active ? "active" : "inactive"}</p>
        <button
          value={`${e.id}`}
          onClick={e.active ? () => disable(e) : () => activate(e)}
          className="btn btn-warning book"
        >
          {e.active ? "Disable" : "Activate"}
        </button>
      </div>
    </>
  ));
  //   return showList;
};
