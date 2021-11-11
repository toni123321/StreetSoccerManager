import React from 'react';
import { Nav } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../css/Login.css";


const SignUpForm = () => {
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
                <div className="sign-up-htm">
                    <div className="group">
                        <label htmlFor="user" className="label">Username</label>
                        <input id="user" type="text" className="input"/>
                    </div>
                    <div className="group">
                        <label htmlFor="pass" className="label">Password</label>
                        <input id="pass" type="password" className="input" data-type="password"/>
                    </div>
                    <div className="group">
                        <label htmlFor="repeat-pass" className="label">Repeat Password</label>
                        <input id="repeat-pass" type="password" className="input" data-type="password"/>
                    </div>
                    <div className="group">
                        <label htmlFor="email" className="label">Email Address</label>
                        <input id="email" type="text" className="input"/>
                    </div>
                    <div className="group">
                        <input type="submit" className="button sign-in-up" value="Sign Up"/>
                    </div>
                    <div className="hr"></div>
                    <div className="foot-lnk">
                    <LinkContainer to="/login">
                        <Nav.Link id="link-already-member">Already a member? </Nav.Link>
                    </LinkContainer>
                    </div>
                </div>
                </div>    
            </div>
        </div>
        
    );
}

export default SignUpForm;