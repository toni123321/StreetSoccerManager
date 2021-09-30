
import React, {useState, useEffect} from "react";

import LoginForm from './LoginForm';
import { useHistory } from 'react-router-dom';
import FrontendUserService from "../services/FrontendUserService";
import UserService from "../services/UserService";

const Login = () => {
    const history = useHistory();


    const [loggedUser, setLoggedUser] = useState({
        id:null,
        email: "",
    });

    const loggedUserId = loggedUser.id;
    const loggedUserEmail = loggedUser.email;

    const [isUserLogged, setIsUserLogged] = useState(false);

    
    const [users, setUsers] = useState();

    

    useEffect(() => {
        retrieveUsers();
    }, []);

    const retrieveUsers = () => {
        UserService.getAll()
          .then(response => {
            setUsers(response.data);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
        })
    };
      

    const [error, setError] = useState("");

    const checkCredentials = (email, password) => {
        return users.find(user => user.email === email && user.password === password);
    };

    
    const handleLogin = (details) => {
        const currentUser = checkCredentials(details.email, details.password);
        console.log(currentUser.id, currentUser.email);
        if(currentUser !== undefined){
            setLoggedUser({
                id: currentUser.id,
                email: currentUser.email
            })
            setError("");
            setIsUserLogged(true);
        }
        else{
            setError("Details do not match!");
        }
        //console.log(loggedUser.id, loggedUser.email);   
    };

    const LogOut = () => {
        setLoggedUser({
            id: null,
            email: ""
        });
        setIsUserLogged(false);
    };


    return (
        <div>
            {(isUserLogged) ?
            (
            <div className="welcome">
                <h1>Welcome, {loggedUser.email}</h1>
                <button onClick={LogOut}>Log out</button>
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