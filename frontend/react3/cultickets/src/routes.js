import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
// import { DeadlinePage } from "./pages/DeadlinePage";
// import { DetailsPage } from "./pages/DetailsPage";
// import { RegistrationPage } from "./pages/RegistrationPage";
// import { CreateDeadlinePage } from "./pages/CreateDeadlinePage";
import { AuthPage } from "./pages/AuthPage";

export const useRoutes = (isAuthentificated) => {
  if (isAuthentificated) {
    return (
      <Routes>
        <Route path="/deadlines" exact>
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
