import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
// import { DeadlinePage } from "./pages/DeadlinePage";
// import { DetailsPage } from "./pages/DetailsPage";
// import { RegistrationPage } from "./pages/RegistrationPage";
// import { CreateDeadlinePage } from "./pages/CreateDeadlinePage";
import { AuthPage } from "./pages/AuthPage";
import { ShowPage } from "./pages/ShowPage";
import { TicketsPage } from "./pages/TicketsPage";

export const useRoutes = (isAuthentificated, roles) => {
  if (isAuthentificated) {
    if (roles.map((value) => value.authority).includes("ADMIN")) {
      return (
        <Routes>
          <Route path="/shows" exact element={<ShowPage />}></Route>
          {/* <Route path="/create" exact>
            <CreateDeadlinePage />
          </Route> */}
          <Route path="/show-details/:id" element={<TicketsPage />}></Route>
          {/* <Navigate to={"/shows"} /> */}
        </Routes>
      );
    } else {
      return (
        <Routes>
          <Route path="/shows" exact element={<ShowPage />}>
            {/* <DeadlinePage /> */}
          </Route>
          <Route path="/create" exact>
            {/* <CreateDeadlinePage /> */}
          </Route>
          <Route path="/details/:id">{/* <DetailsPage /> */}</Route>
          {/* <Navigate to={"/deadlines"} /> */}
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
