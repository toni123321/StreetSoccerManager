import React from "react";
import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../css/MainNavbar.scss";
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router-dom';
import SignOut from "./SignOut/SignOut";
import {useAuth} from "../hooks/use-auth.js";

function MainNavbar() {
    const cookies = new Cookies();
    const token = cookies.get('login-token');
    console.log(token);
    
    // function handleLogOut() {
    //     cookies.remove('login-token', {sameSite: 'lax'});
    //     //setIsUserLogged(false);
    //     localStorage.removeItem("team");
    // }

    let history = useHistory();
    let auth = useAuth();

    return (
            <div>
                <div>
                <Navbar className="main-navbar" collapseOnSelect expand="lg"  variant="dark">
                    <Container>
                        <Navbar.Brand id="nav-brand">Street Soccer Manager</Navbar.Brand>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                        <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav>
                            
                            <LinkContainer to="/" exact >
                                <Nav.Link ><i className="fas fa-home"></i> Home</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/game" exact>
                                <Nav.Link ><i className="fas fa-gamepad"></i> Game</Nav.Link>
                            </LinkContainer>
                            
                            <LinkContainer to="/about" >
                                <Nav.Link ><i className="fas fa-info"></i> About</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/contact">
                                <Nav.Link ><i className="far fa-address-book"></i> Contact</Nav.Link>
                            </LinkContainer>
                           
                           {/* <LinkContainer to="/account">
                                    <Nav.Link ><i class="fas fa-user"></i> View account</Nav.Link>
                            </LinkContainer>
                            <button  onClick={() => {
                                        auth.signout(() => history.push("/"));
                                    }} className="active nav-link active logout">
                                        <i class="fas fa-sign-out-alt"></i> Log out
                            </button> */}
                            
                            {auth.isUserLogged ?
                            (
                            <>  
                            <NavDropdown title="Account" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/account">
                                <div>View account</div>
                            </NavDropdown.Item>
                            <NavDropdown.Item>
                                <div onClick={() => {
                                        auth.signout(() => history.push("/"));
                                    }}>
                                        <i class="fas fa-sign-out-alt"></i> Log out
                                </div>
                            </NavDropdown.Item>
                            </NavDropdown>
                            </>
                            ):(
                            <>
                            <LinkContainer to="/loginPage" >
                            <Nav.Link ><i className="fas fa-sign-in-alt"></i> Log in</Nav.Link>
                            </LinkContainer>
                            </>)}
                        </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
                </div>
            </div>
    )
}

export default MainNavbar;