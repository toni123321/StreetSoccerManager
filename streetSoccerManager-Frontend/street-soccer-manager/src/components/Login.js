
import React, {useState, useEffect} from "react";
import LoginForm from './LoginForm';
import { useHistory } from 'react-router-dom';
import FrontendUserService from "../services/FrontendUserService";
import UserService from "../services/UserService";
import axios from "axios";
import Cookies from 'universal-cookie';
import jwt_decode from "jwt-decode";

const Login = () => {
    const cookies = new Cookies();
 
    const [isUserLogged, setIsUserLogged] = useState(false);
    const [loginError, setLoginError] = useState(false);

    useEffect(() => {
        if(cookies.get('login-token') != null){
            setIsUserLogged(true);
        } 
    }, []);

    async function handleLogin(details) {
        try{
            setLoginError(false);
            const response = await UserService.login(details);
            console.log(response.data.Authorization);
            
            const token = response.data.Authorization;
            const decode_token = jwt_decode(token);

            cookies.set('login-token', token, {sameSite: 'lax'});
            console.log(cookies.get('login-token'));
            setIsUserLogged(true);
        }
        catch (error) {
            setLoginError(true);
        }
    }

    function handleLogout() {
        cookies.remove('login-token', {sameSite: 'lax'});
        setIsUserLogged(false);
    }

    return (
        <div>
            {(isUserLogged) ?
            (
            <div className="welcome">
                <h1>Welcome</h1>
                <button onClick={handleLogout}>Log out</button>
            </div>
            ) :
            (
            <>
            {loginError ? 
            (
            <>
            <h3>Wrong credentials</h3>
            </>
            ) 
            : (<></>)}
            <LoginForm handleLogin={handleLogin}/>
            </>
            )
            }
            
        </div>
    );
}

export default Login;