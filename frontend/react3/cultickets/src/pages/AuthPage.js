import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import { useHttp } from "../hooks/http.hook";
import { useMessage } from "../hooks/message.hook";
import { Link, NavLink } from "react-router-dom";
import { Loader } from "../components/Loader";

export const AuthPage = () => {
  const auth = useContext(AuthContext);
  const message = useMessage();
  const { loading, request, error, clearError } = useHttp();
  const [form, setForm] = useState({
    login: "",
    password: "",
  });

  useEffect(() => {
    message(error);
    clearError();
  }, [error, message, clearError]);

  const changeHandler = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const loginHandler = async () => {
    try {
      console.log("Here");
      const data = await request("/login", "POST", { ...form });
      console.log(data);
      auth.login(data.token, data.user.userId, data.user.login, data.roles);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="row">
      <div className="col s6 offset-s3">
        <h1>Cultickets</h1>
        <div className="card blue-grey lighten-4">
          <div class="d-flex justify-content-center">
            <div className="card-content white-text">
              <h4>Authorization</h4>
              <div className="input-field">
                <label htmlFor="Email">Email</label>
                <br />
                <input
                  id="login"
                  type="text"
                  name="login"
                  value={form.login}
                  onChange={changeHandler}
                />
              </div>
              <div className="input-field">
                <label htmlFor="Password">Password</label>
                <br />
                <input
                  id="password"
                  type="password"
                  className="validate"
                  name="password"
                  value={form.password}
                  onChange={changeHandler}
                />
              </div>
              <div
                className="card-action"
                class="d-flex justify-content-around"
              >
                <Link
                  className="btn yellow darken-4"
                  onClick={loginHandler}
                  to="/shows"
                >
                  Sign in
                </Link>
                <Link
                  className="btn grey lighten-1 black-text"
                  to="/registration"
                  disabled={loading}
                >
                  Sign up
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
