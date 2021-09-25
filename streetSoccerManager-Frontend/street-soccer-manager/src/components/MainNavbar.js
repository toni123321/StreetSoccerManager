import React from "react";
import { BrowserRouter, NavLink} from 'react-router-dom';

import Routers from "./Routers";

import { Navbar, Nav, Container } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../css/MainNavbar.css";

function MainNavbar() {
    return (
        <BrowserRouter>
            <div>
                <>
                <Navbar className="main-navbar" collapseOnSelect expand="lg" bg="primary" variant="dark">
                    <Container>
                        <Navbar.Brand id="nav-brand">Street Soccer Manager</Navbar.Brand>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                        <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav>
                            
                            <LinkContainer to="/" exact>
                                <Nav.Link ><i className="fas fa-home"></i> Home</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/about">
                                <Nav.Link ><i className="fas fa-info"></i> About</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/contact">
                                <Nav.Link ><i className="far fa-address-book"></i> Contact</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/login">
                            <Nav.Link ><i className="fas fa-sign-in-alt"></i> Login</Nav.Link>
                            </LinkContainer>
                            
                        </Nav>
                        {/* <Nav> */}
                            {/* <LinkContainer to="/signup">
                            <Nav.Link><i className="fas fa-user-plus"></i> Signup</Nav.Link>
                            </LinkContainer> */}
                            
                            {/* <LinkContainer to="/profile">
                            <Nav.Link><i className="fas fa-user"></i> Profile</Nav.Link>
                            </LinkContainer>     */}
                        {/* </Nav> */}
                        </Navbar.Collapse>
                        
                    
                    </Container>
                </Navbar>
                </>

                <div className="container mt-3">
                    <Routers/>
                </div>
            </div>
        </BrowserRouter>
    )
}

export default MainNavbar;