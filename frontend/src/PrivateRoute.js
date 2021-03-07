import React from "react";
import { Redirect, Route } from "react-router-dom";

const PrivateRoute = ({ path, component: Component, ...rest }) => {
const isLoggedIn = sessionStorage.getItem("access_token");

  return (
    <Route
      path={path}
      {...rest}
      render={props => isLoggedIn 
        ? <Component {...props} />
        : <Redirect to={{ pathname: "/login", state: { from: props.location } }} />
      }
    />
  );
};

export default PrivateRoute;