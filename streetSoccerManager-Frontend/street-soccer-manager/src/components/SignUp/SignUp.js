import React, {useState} from 'react';
import styles from "../../css/Login.scss";
import SignUpForm from './SignUpForm';
import {
    useHistory,
    useLocation
  } from "react-router-dom";
  import {useAuth} from "../../hooks/use-auth.js";

const SignUp = () => {
    let location = useLocation();
    let auth = useAuth();
    const history = useHistory();

    const [registerError, setRegisterError] = useState(false);
    const [successRegistration, setSuccessRegistration] = useState(false);
  
    let { from } = location.state || { from: { pathname: "/game" } };

    function handleRegister (details) {
      setRegisterError(false);
      
      auth.signup(details, from).then(data => {
        alert(`Successful registration, ${data}. Let's now log in!`);
        history.push("/loginPage");
      })
      .catch(err => {
        setRegisterError(true);
      })
    };

  
    return (
      <div>
        {/* <p>You must log in to view the page at {from.pathname}</p> */}
        {registerError ? 
            (
            <>
            <h3>Wrong registration details!</h3>
            </>
            ) 
            :
            (<></>)
            
        }
        <SignUpForm handleRegister={handleRegister}/>
      </div>
    );
}

export default SignUp;