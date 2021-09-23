import React from "react";
import { BrowserRouter, Link } from 'react-router-dom';


import "bootstrap/dist/css/bootstrap.min.css";
import Routers from "./Routers";


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
                    <Routers/>
                </div>
            </div>
        </BrowserRouter>
    )
}

export default MainNavbar;