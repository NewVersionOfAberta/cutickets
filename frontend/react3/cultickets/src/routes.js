import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { AuthPage } from "./pages/AuthPage";
import { ShowPage } from "./pages/ShowPage";
import { TicketsPage } from "./pages/TicketsPage";
import { ScheduledShowPage } from "./pages/ScheduledShowPage";
import { Profile } from "./pages/Profile";
import { StatisticsPage } from "./pages/StatisticsPage";
import { AdminPage } from "./pages/AdminPage";
import { JornalPage } from "./pages/JornalPage";

export const useRoutes = (isAuthentificated, roles) => {
  if (isAuthentificated) {
    if (roles.map((value) => value.authority).includes("ADMIN")) {
      return (
        <Routes>
          <Route path="/shows" exact element={<ShowPage />}></Route>
          {/* <Route path="/create" exact>
            <CreateDeadlinePage />
          </Route> */}
          <Route
            path="/scheduled-show/:id"
            element={<ScheduledShowPage />}
          ></Route>
          <Route path="/tickets/:id" element={<TicketsPage />}></Route>
          <Route path="/profile" exact element={<Profile />}></Route>
          <Route path="/statistics" exact element={<StatisticsPage />}></Route>
          <Route path="/admin" exact element={<AdminPage />}></Route>
          <Route path="/jornal" exact element={<JornalPage />}></Route>
          {/* <Navigate to={"/shows"} /> */}
        </Routes>
      );
    } else {
      return (
        <Routes>
          <Route path="/shows" exact element={<ShowPage />}></Route>
          {/* <Route path="/create" exact>
            <CreateDeadlinePage />
          </Route> */}
          <Route
            path="/scheduled-show/:id"
            element={<ScheduledShowPage />}
          ></Route>
          <Route path="/tickets/:id" element={<TicketsPage />}></Route>
          <Route path="/profile" exact element={<Profile />}></Route>
          <Route path="/statistics" exact element={<StatisticsPage />}></Route>
          {/* <Navigate to={"/shows"} /> */}
        </Routes>
      );
    }
  }
  return (
    <Routes>
      <Route path="/registration" exact>
        {/* <RegistrationPage /> */}
      </Route>
      <Route path="/" exact element={<AuthPage />}>
        {/* <AuthPage /> */}
      </Route>
      {/* <Navigate to="/" /> */}
    </Routes>
  );
};
