import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import { useHttp } from "../hooks/http.hook";
import { useMessage } from "../hooks/message.hook";
import { Link } from "react-router-dom";
import { Loader } from "../components/Loader";
import "materialize-css";

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

  // const registerHandler = useCallback(async () => {
  //   try {
  //     const data = await request("/api/auth/register", "POST", { ...form });
  //     message(data.message);
  //   } catch (e) {}
  // }, [form, message, request]);

  const loginHandler = async () => {
    try {
      console.log("Here");
      const data = await request("/login", "POST", { ...form });
      auth.login(data.token, data.userId, data.name);
    } catch (e) {
      console.log(e);
    }
  };

  if (loading) {
    return <Loader />;
  }

  return (
    <div className="row">
      <div className="col s6 offset-s3">
        <h1>Cultickets</h1>
        <div className="card blue-grey lighten-4">
          <div className="card-content white-text">
            <span className="card-title blue-grey-text darken-2">
              Authorization
            </span>
            <div className="input-field">
              <input
                id="login"
                type="text"
                name="login"
                value={form.login}
                onChange={changeHandler}
              />
              <label htmlFor="Email">Email</label>
            </div>
            <div className="input-field">
              <input
                id="password"
                type="password"
                className="validate"
                name="password"
                value={form.password}
                onChange={changeHandler}
              />
              <label htmlFor="Password">Password</label>
            </div>
          </div>
          <div className="card-action">
            <button className="btn yellow darken-4" onClick={loginHandler}>
              Sign in
            </button>
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
  );
};
