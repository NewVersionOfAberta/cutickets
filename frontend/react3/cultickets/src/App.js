import { BrowserRouter as Router } from "react-router-dom";
import "./App.css";
import { useRoutes } from "./routes";
import React from "react";
import { useAuth } from "./hooks/auth.hook";
import { AuthContext } from "./context/AuthContext";
import { Navbar } from "./components/Navbar";

function App() {
  const { token, login, logout, userId, userName } = useAuth();
  const isAuthentificated = !!token;
  const routes = useRoutes(isAuthentificated);
  return (
    <AuthContext.Provider
      value={{
        token,
        login,
        logout,
        userId,
        userName,
        isAuthentificated,
      }}
    >
      <Router>
        {isAuthentificated && <Navbar />}
        <div className="container">{routes}</div>
      </Router>
    </AuthContext.Provider>
  );
}

export default App;
