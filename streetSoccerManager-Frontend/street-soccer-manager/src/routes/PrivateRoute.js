import React from 'react';
import {useAuth} from "../hooks/use-auth.js";
import {
  Route,
  Redirect
} from "react-router-dom";

function PrivateRoute({ children, ...rest }) {
    let auth = useAuth();
    return (
        <Route
        {...rest}
        render={({ location }) =>
            auth.isUserLogged ? (
            children
            ) : (
            <Redirect
                to={{
                pathname: "/loginPage",
                state: { from: location }
                }}
            />
            )
        }
        />
    );
}

export default PrivateRoute;