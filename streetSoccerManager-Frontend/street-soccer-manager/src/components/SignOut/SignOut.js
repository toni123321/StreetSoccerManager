import React, {useEffect} from 'react';
import {
    useHistory,
} from "react-router-dom";
import {useAuth} from "../../hooks/use-auth.js";


function SignOut(props) {
    let history = useHistory();
    let auth = useAuth();

    return auth.isUserLogged ? (
      <p>
        Welcome!{" "}
        <button
          onClick={() => {
            auth.signout(() => history.push("/"));
          }}
        >
          Sign out
        </button>
      </p>
    ) : (
      <p>You are not logged in.</p>
    );
}

export default SignOut;