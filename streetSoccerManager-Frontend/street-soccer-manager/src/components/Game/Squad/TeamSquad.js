import React, {useEffect, useState} from 'react';
import styles from "../../../css/TeamSquad.scss";
import PlayerService from '../../../services/PlayerService';
import Player from "../Player/Player";
import {Container, Col, Row} from 'react-bootstrap';
import RotatePlayersContainer from '../PlayerRotation/RotatePlayersContainer';
import Cookies from 'universal-cookie';

const TeamSquad = () => {
    const cookies = new Cookies();
    const token = cookies.get('login-token');

    const initialTeamState = {
        id: null,
        name: "",
        formation: {
            id:null,
            name: ""
        },
        manager: {
            id: null,
            email: "",
            password: "",
            nickname: ""
        }
    }

    const [team, setTeam] = useState(initialTeamState);
    const [players, setPlayers] = useState([]);

    const [rotationMode, setRotationMode] = useState(false);

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          retrievePlayers(foundTeam.id);
        } 
    }, []);
    
    async function retrievePlayers(teamId) {
        const response = await PlayerService.getAllInTeam(teamId, token);
        setPlayers(response.data);
    }

    function handleRotationOfPlayers (playerOut, playerIn) {

        const newPlayersList = players.map((item) => {
            if (item.id === playerOut.id) {
                const updatedItem = {
                    ...item,
                    playerPositionInfo : playerOut,
                };
        
                return updatedItem;
            }
            else if(item.id === playerIn.id){
                const updatedItem = {
                    ...item,
                    playerPositionInfo : playerIn,
                    };
            
                    return updatedItem;
            }
      
            return item;
        });
      
        setPlayers(newPlayersList);
        setRotationMode(false);
    }
    
    function changeRotationMode (){
        setRotationMode(!rotationMode);
    }

    return (
        <>
        {rotationMode ? (
            <RotatePlayersContainer handleRotationOfPlayers={handleRotationOfPlayers} changeRotationMode={changeRotationMode}/>
        )
        :
        (
        <>
        <h1 className="teamName">Team: {team.name}</h1>
        <h3 className="teamManager">Manager: {team.manager.nickname}</h3>
        <h3 className="teamFormation">Formation: {team.formation.name}</h3>

        <div className="team-squad">
            <Container className="startingTeam">
            <Row className="starting-players-row-1">
                {players && players.filter(player => player.playerPositionInfo.starting == true && ["LW", "ST", "RW"].includes(player.playerPositionInfo.currentPosition.name)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col className={`squad-col-1${index}`}>
                <Player key={player.id} player={player} rotationMode={false} changeRotationMode={changeRotationMode}/>
                </Col>
                ))}
            </Row>
            <Row className="starting-players-row-2">
                {players && players.filter(player => player.playerPositionInfo.starting == true && ["LM", "CM", "RM"].includes(player.playerPositionInfo.currentPosition.name)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col className={`squad-col-2${index}`}>
                <Player key={player.id} player={player} rotationMode={false} changeRotationMode={changeRotationMode}/>
                </Col>
                ))}
            </Row>
            <Row className="starting-players-row-3">
                {players && players.filter(player => player.playerPositionInfo.starting == true && ["LB", "CB", "RB"].includes(player.playerPositionInfo.currentPosition.name)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col className={`squad-col-3${index}`}>
                <Player key={player.id} player={player} rotationMode={false} changeRotationMode={changeRotationMode}/>
                </Col>
                ))}
            </Row>
            <Row className="starting-players-row-4">
                {players && players.filter(player => player.playerPositionInfo.starting == true && ["GK"].includes(player.playerPositionInfo.currentPosition.name)).sort((a, b) => a.playerPositionInfo.currentPosition.id - b.playerPositionInfo.currentPosition.id).map((player, index) => (
                <Col className={`squad-col-4${index}`}>
                <Player key={player.id} player={player} rotationMode={false} changeRotationMode={changeRotationMode}/>
                </Col>
                ))}
            </Row>

            </Container>
            <div className="reserves">
                {players && players.filter(player => player.playerPositionInfo.starting == false).map((player) => (
                <Player key={player.id} player={player} rotationMode={false} changeRotationMode={changeRotationMode}/>
                ))}
            </div>
        </div>
        </>
        )}
        
        </>
    );
}

export default TeamSquad;