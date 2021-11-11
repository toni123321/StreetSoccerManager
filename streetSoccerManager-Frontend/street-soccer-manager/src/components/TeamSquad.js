import React, {useEffect, useState} from 'react';
import StartingPlayersContainer from "./StartingPlayersContainer";
import styles from "../css/TeamSquad.css";
import PlayerService from '../services/PlayerService';
import Player from "./Player";
import {Container, Col, Row} from 'react-bootstrap';



const TeamSquad = () => {
    
    const initialTeamState = {
        id: null,
        name: "",
        formation: {
            id:null,
            name: ""
        },
        manager: {
            "id": null,
            "email": "",
            "password": "",
            "nickname": "",
            "points": null
        }
    }

    const initialPlayerState = {
        id: null,
        playerPersonalInfo: null,
        playerPositionInfo: null,
        playerTeamInfo: null,
        playerAdditionalInfo: null
    }
    
    const [team, setTeam] = useState(initialTeamState);
    const [startingPlayers, setStartingPlayers] = useState([]);
    const [reserves, setReserves] = useState([]);

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          retrieveStartingPlayers(foundTeam.id);
          retrieveReserves(foundTeam.id);
          console.log("Reload");
        } 
    }, []);
    
    // retrieve starting players
    async function retrieveStartingPlayers(teamId) {
        const response = await PlayerService.getStartingPlayers(teamId);
        console.log("Starting players: ", response.data);
        setStartingPlayers(response.data);
    }

    // retrieve reserves
    async function retrieveReserves(teamId) {
        const response = await PlayerService.getReserves(teamId);
        console.log("Reserves: ", response.data);
        setReserves(response.data);
    }

    return (
        <>
        <h1>Team: {team.name}</h1>
        <h3>Manager: {team.manager.nickname}</h3>
        <h3>Formation: {team.formation.name}</h3>

        <div className="team-squad">
            <Container className="startingTeam">
            <Row>
                {startingPlayers && startingPlayers.filter(player => player.playerPositionInfo.starting == true && ["LW", "ST", "RW"].includes(player.playerPositionInfo.currentPosition.position)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col>
                <Player key={player.id} player={player} rotationMode={false}/>
                </Col>
                ))}
            </Row>
            <Row>
                {startingPlayers && startingPlayers.filter(player => player.playerPositionInfo.starting == true && ["LM", "CM", "RM"].includes(player.playerPositionInfo.currentPosition.position)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col>
                <Player key={player.id} player={player} rotationMode={false}/>
                </Col>
                ))}
            </Row>
            <Row>
                {startingPlayers && startingPlayers.filter(player => player.playerPositionInfo.starting == true && ["LB", "CB", "RB"].includes(player.playerPositionInfo.currentPosition.position)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col>
                <Player key={player.id} player={player} rotationMode={false}/>
                </Col>
                ))}
            </Row>
            <Row>
                {startingPlayers && startingPlayers.filter(player => player.playerPositionInfo.starting == true && ["GK"].includes(player.playerPositionInfo.currentPosition.position)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col>
                <Player key={player.id} player={player} rotationMode={false}/>
                </Col>
                ))}
            </Row>

            </Container>
            <div className="reserves">
                {reserves && reserves.filter(player => player.playerPositionInfo.starting == false).map((player) => (
                <Player key={player.id} player={player} rotationMode={false}/>
                ))}
            </div>
        </div>
        </>
    );
}

export default TeamSquad;