import React, {useState} from 'react';
import { Nav } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../../css/Login.scss";

const LoginForm = ({handleLogin}) => {

    const detailsState = {
        email: "",
        password: ""
    }

    const [details, setDetails] = useState(detailsState)

    const handleInput = (e) => {
        const {name, value} = e.target;
        setDetails({...details, [name]: value}); 
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        handleLogin(details);
        setDetails(detailsState);
    }

    return (
        <div className="login-wrap">
            <div className="login-html">
                <LinkContainer to="/loginPage">
                    <Nav.Link className="singInUp-links">Sign in</Nav.Link>
                </LinkContainer>

                <LinkContainer to="/signUp">
                    <Nav.Link className="singInUp-links">Sign up</Nav.Link>
                </LinkContainer>
                
                <div className="login-form">
                <form className="sign-in-htm" id="sign-in" onSubmit={handleSubmit}>

                    <div className="group">
                        <label htmlFor="email" className="label">Email</label>
                        <input name="email" placeholder='email...' value={details.email} id="email" type="text" className="input" onChange={handleInput}/>
                    </div>
                    <div className="group">
                        <label htmlFor="password" className="label">Password</label>
                        <input name="password" placeholder='password...' value={details.password} id="password" type="password" className="input" data-type="password" onChange={handleInput}/>
                    </div>
                    <div className="group">
                        <input id="check" type="checkbox" className="check" />
                        <label htmlFor="check">Keep me Signed in</label>
                    </div>
                    <div className="group">
                        <input type="submit" className="button sign-in-up" value="Sign In"/>
                    </div>
                    <div className="hr"></div>
                    <div className="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                    </div>
                </form>
                </div>    
            </div>
        </div>
        
    );
}

export default LoginForm;