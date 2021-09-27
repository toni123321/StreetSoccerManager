import React, {useState} from 'react';
import { Nav } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../css/Login.css";

const LoginForm = ({Login, error}) => {

    const [details, setDetails] = useState({
        email: "",
        password: ""
    })

    const handleInput = (e) => {
        const {name, value} = e.target;
        setDetails({...details, [name]: value}); 
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        Login(details);

    }

    return (
        <div className="login-wrap">
            <div className="login-html">
                <LinkContainer to="/login">
                    <Nav.Link className="singInUp-links">Sign in</Nav.Link>
                </LinkContainer>

                <LinkContainer to="/signUp">
                    <Nav.Link className="singInUp-links">Sign up</Nav.Link>
                </LinkContainer>
                
                <div className="login-form">
                <div className="sign-in-htm">
                    <div className="group">
                        <label htmlFor="user" className="label">Username</label>
                        <input id="user" type="text" className="input"/>
                    </div>
                    <div className="group">
                        <label htmlFor="pass" className="label">Password</label>
                        <input id="pass" type="password" className="input" data-type="password"/>
                    </div>
                    <div className="group">
                        <input id="check" type="checkbox" className="check" />
                        <label htmlFor="check">Keep me Signed in</label>
                    </div>
                    <div className="group">
                        <input type="submit" className="button" value="Sign In"/>
                    </div>
                    <div className="hr"></div>
                    <div className="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                    </div>
                </div>
                </div>    
            </div>
        </div>
        
    );
}

export default LoginForm;