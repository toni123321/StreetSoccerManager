
import React, {useState, useEffect} from "react";
import LoginForm from './LoginForm';
import { useHistory } from 'react-router-dom';
import FrontendUserService from "../services/FrontendUserService";
import UserService from "../services/UserService";
import axios from "axios";
import Cookies from 'universal-cookie';
import jwt_decode from "jwt-decode";
import TeamService from "../services/TeamService";

const Login = () => {
    let history = useHistory();
    const cookies = new Cookies();
 
    const [isUserLogged, setIsUserLogged] = useState(false);
    const [loginError, setLoginError] = useState(false);

    useEffect(() => {
        if(cookies.get('login-token') !== undefined){
            setIsUserLogged(true);
            // history.push("/game");
        } 
    }, []);

    function handleLogin(details) {
        setLoginError(false);
        UserService.login(details)
        .then(response => {
            console.log(response.data.Authorization);
            const token = response.data.Authorization;
            const decode_token = jwt_decode(token);

            cookies.set('login-token', token, {sameSite: 'lax'});
            console.log(cookies.get('login-token'));
            setIsUserLogged(true);
            console.log(decode_token.sub);
            getUserByEmail(decode_token.sub, token);
            history.push("/game");   
        })
        .catch (error => {
            setLoginError(true);
        })
    }

    function getUserByEmail(email, token) {
        UserService.getUserByEmail(email, token)
        .then((response) => {
            getUserTeam(response.data.id, token);
        }
        );
        // const response = await UserService.getUserByEmail(email, token);
        // console.log(response.data);
    }

    function getUserTeam(userId, token){
         TeamService.getTeamByUserId(userId, token)
        .then((response) => {
            localStorage.setItem("team", JSON.stringify(response.data));
        }
        );
    }

    function handleLogout() {
        cookies.remove('login-token', {sameSite: 'lax'});
        setIsUserLogged(false);
        localStorage.removeItem("team");
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