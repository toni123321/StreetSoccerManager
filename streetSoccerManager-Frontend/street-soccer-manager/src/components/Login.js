import React, {useState, useEffect} from "react";
import { useHistory } from 'react-router-dom';
import FrontendUserService from "../services/FrontendUserService";


import LoginForm from "./LoginForm";


function Login() {

    const history = useHistory();

    const [user, setUser] = useState({
        email: "",
        password: ""
    });

    
    const [users, setUsers] = useState();
    useEffect(() => {
        retrieveUsers();
    }, [])

    const retrieveUsers = () => {
        FrontendUserService.getAll()
          .then(response => {
            setUsers(response.data);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
        })
    }
      

    const [error, setError] = useState("");

    const checkCredentials = (email, password) => {
        return users.find(user => user.email === email && user.password === password)
    }

    const LogIn = details => {
        console.log(checkCredentials(details.email, details.password));
        // if(checkCredentials(details.email, details.password)){
        //     setUser({
        //         name: details.name,
        //         password: details.password
        //     })
        //     setError("");
        // }
        // else{
        //     setError("Details do not match!");
        // }
    }

    const LogOut = () => {
        setUser({
            email: "",
            password: ""
        });
    }


    return (
        <div>
            {(user.email !== "") ?
            (
            <div className="welcome">
                <h1>Welcome</h1>
                <button onClick={LogOut}>Log out</button>
            </div>
            ) :
            (<LoginForm Login={LogIn} error={error}/>)
            }
            
        </div>
    );
}
export default Login;