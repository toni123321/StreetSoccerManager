import React from "react";
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';
import AboutPage from "./AboutPage";
import HomePage from "./HomePage";
import LoginPage from "./LoginPage";

import "bootstrap/dist/css/bootstrap.min.css";


function MainNavbar() {
    return (
        <BrowserRouter>
            <div>

                <div>
                    <Link to={"/"} className="nav-link">
                    Home
                    </Link>
                   
                    <Link to={"/about"} className="nav-link">
                        About
                    </Link> 
                    
                    <Link to={"/login"} className="nav-link">
                        Login
                    </Link>
                </div>

                <div className="container mt-3">
                    <Switch>
                        <Route exact path="/" component={HomePage} />
                        <Route exact path="/about" component={AboutPage} />
                        <Route exact path="/login" component={LoginPage} />
                    </Switch>
                </div>
            </div>
        </BrowserRouter>
    )
}

export default MainNavbar;