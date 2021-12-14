import React, { useContext } from "react";
import { NavLink } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

export const Navbar = () => {
  // const history = useHistory();
  const auth = useContext(AuthContext);

  const logoutHandler = (event) => {
    auth.logout();
    // history.push("/");
  };

  return (
    <nav>
      <div className="nav-wrapper blue-grey darken-1">
        <span className="brand-logo">Deadlines</span>
        <ul id="nav-mobile" className="right hide-on-med-and-down">
          <li>
            <NavLink to="/create">Create</NavLink>
          </li>
          <li>
            <NavLink to="/deadlines">Deadlines</NavLink>
          </li>
          <li>
            <NavLink to="/" onClick={logoutHandler}>
              Log out
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
};
