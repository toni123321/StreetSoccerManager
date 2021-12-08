import {React, useEffect} from 'react';
import styles from "../../../css/GameNavbar.scss"
import { Nav, Row, Col, Container } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router-dom';

const GameNavbar = ({team}) => {    
    return (
        <div className="game-navbar-container">
       
        <Container className="game-navbar">
        {/* <Row id="nav-items-frow" className="team-name-row">
            <h2 className="team-name">Team name: {team.name}</h2>
        </Row> */}
        <Row id="nav-items-frow" className="justify-content-sm-center game-nav-first-view">
            <Col xs="12" md="5" className="nav-item" id="playMatch">
                <LinkContainer to="/chooseOpponent">
                    <Nav.Link>Play match</Nav.Link>

                </LinkContainer>
            </Col>
            <Col xs="12" md="5" className="nav-item" id="playTournament">
                <LinkContainer to="#">
                    <Nav.Link>Play tournament</Nav.Link>
                </LinkContainer>
            </Col>
        </Row>
        <Row id="nav-items-srow" className="game-nav-second-view">
            <Col xs="12" md="4" className="nav-item" id="trainPlayers">
                <LinkContainer to="#">
                    <Nav.Link>Train players</Nav.Link>
                </LinkContainer>
            </Col>
            <Col xs="12" md="3" className="nav-item" id="teamSquad">
                <LinkContainer to="/teamSquad">
                    <Nav.Link>Team squad</Nav.Link>
                </LinkContainer>
            </Col>
            <Col xs="12" md="3" className="nav-item" id="shop">
                <LinkContainer to="#">
                    <Nav.Link>Shop</Nav.Link>
                </LinkContainer>  
            </Col>
            {/* <Col className="nav-item" id="settings">
                <LinkContainer to="#">
                    <Nav.Link>Settings</Nav.Link>
                </LinkContainer>
            </Col> */}
            
        </Row>
        </Container>
        </div>
    );
}

export default GameNavbar;