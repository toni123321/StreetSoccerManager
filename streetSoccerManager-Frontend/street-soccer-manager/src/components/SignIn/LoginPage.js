import React, {useState} from "react";
import LoginForm from './LoginForm';
import {
  useHistory,
  useLocation
} from "react-router-dom";
import {useAuth} from "../../hooks/use-auth.js";

function LoginPage() {
    let history = useHistory();
    let location = useLocation();
    let auth = useAuth();

    const [loginError, setLoginError] = useState(false);
  
    let { from } = location.state || { from: { pathname: "/game" } };
    
    function handleLogin (details) {
      setLoginError(false);

      auth.signin(details, from)
      .then(data => {
        alert(`Successfull login`);
        history.replace(from);
      })
      .catch(err => {
        console.log(err);
        setLoginError(true);
      })
    };
  
    return (
      <div>
        {/* <p>You must log in to view the page at {from.pathname}</p> */}
        {loginError ? 
            (
            <>
            <h3>Wrong credentials</h3>
            </>
            ) 
            : (<></>)
        }
        <LoginForm handleLogin={handleLogin}/>
      </div>
    );
}

export default LoginPage;