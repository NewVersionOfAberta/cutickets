import React, { useContext, useState } from "react";
import { Button, Nav, NavDropdown, MenuItem, NavItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { AuthContext } from "../context/AuthContext";

export const Navbar = () => {
  // const history = useHistory();
  const auth = useContext(AuthContext);
  const [isActive, setActive] = useState([false, false, false, false]);
  const logoutHandler = (event) => {
    auth.logout();
    // history.push("/");
  };

  const setPreActive = (index) => {
    let active = [false, false, false, false];
    active[index - 1] = true;
    setActive(active);
  }

  return (
    <Nav className="nav nav-pills nav-justified" onSelect={(key) => setPreActive(key)}>
        <LinkContainer className={`nav-link ${isActive[0] ? "active" : ""}`} to="/profile">
           <NavItem eventkey={1}>Profile</NavItem>
        </LinkContainer>
        <LinkContainer className={`nav-link ${isActive[1] ? "active" : ""}`} to="/shows">
          <NavItem eventkey={2}>Shows</NavItem>
        </LinkContainer>
      
        <LinkContainer className={`nav-link ${isActive[2] ? "active" : ""}`} to="/" onClick={logoutHandler}>
          <NavItem eventkey={3}>Log out</NavItem>
        </LinkContainer>
        <LinkContainer className={`nav-link ${isActive[3] ? "active" : ""}`} to="/" onClick={logoutHandler}>
          <NavItem eventkey={4}>Admin</NavItem>
        </LinkContainer>
    </Nav>
  );
};
