import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
// import { DeadlinePage } from "./pages/DeadlinePage";
// import { DetailsPage } from "./pages/DetailsPage";
// import { RegistrationPage } from "./pages/RegistrationPage";
// import { CreateDeadlinePage } from "./pages/CreateDeadlinePage";
import { AuthPage } from "./pages/AuthPage";

export const useRoutes = (isAuthentificated) => {
  if (isAuthentificated) {
    return (
      <Switch>
        <Route path="/deadlines" exact>
          <AuthPage />
        </Route>
        {/* <Route path="/create" exact>
          <CreateDeadlinePage />
        </Route>
        <Route path="/details/:id">
          <DetailsPage />
        </Route>
        <Redirect to={"/deadlines"} /> */}
      </Switch>
    );
  }
  return (
    <Switch>
      <Route path="/registration" exact>
        {/* <RegistrationPage /> */}
      </Route>
      <Route path="/" exact>
        {/* <AuthPage /> */}
      </Route>
      <Redirect to="/" />
    </Switch>
  );
};
