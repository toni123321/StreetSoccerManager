
import React, {useState, useEffect} from "react";
import LoginForm from './LoginForm';
import { useHistory } from 'react-router-dom';
import FrontendUserService from "../services/FrontendUserService";
import UserService from "../services/UserService";
import axios from "axios";

const Login = () => {

    const [isUserLogged, setIsUserLogged] = useState(false);
    async function handleLogin(details) {
        try{
            const response = await UserService.login(details);
            console.log(response.status);
            setIsUserLogged(true);
        }
        catch (error) {
            console.log(error.response.data);
        }
        
        
    }

    return (
        <div>
            {(isUserLogged) ?
            (
            <div className="welcome">
                <h1>Welcome</h1>
                <button>Log out</button>
            </div>
            ) :
            (
            <LoginForm handleLogin={handleLogin}/>
            )
            }
            
        </div>
    );
}

export default Login;