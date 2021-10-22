import React, {useState, useEffect} from 'react';
import Player from "./Player";
import { useDrop } from "react-dnd";
import FormationPositionBox from './FormationPositionBox';
import FormationPositionService from '../services/FormationPositionService';
import ReservesPanel from './ReservesPanel';
import PlayerService from '../services/PlayerService';
import SwapPlayerContainer from './SwapPlayerContainer';
import {Container, Col, Row} from 'react-bootstrap';


const StartingPlayersContainer = () => {
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

    const playerInitialState = {
      id: null,
      positionIndex: null,
      firstName: "",
      lastName: "",
      dob: null,
      price: null,
      defaultPosition: null,
      currentPosition: null,
      kitNr: null,
      playerStats: null,
      team: null,
      starting: true
    }
  
    const [team, setTeam] = useState(initialTeamState);
    const [players, setPlayers] = useState([]); 

    const [playerForRotation, setPlayerForRotation] = useState(playerInitialState);
    const [swapDest, setSwapDest] = useState(playerInitialState);

    const [origin, setOrigin] = useState(playerInitialState);
    const [dest, setDest] = useState(playerInitialState);
    
    const [displaySwapContainer, setDisplaySwapContainer] = useState(false);
    const [value, setValue] = useState();

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          console.log("here");
          //retrieveStartingPlayers(foundTeam.id);
          //retrieveReserves(foundTeam.id);
          retrievePlayers(foundTeam.id);
        } 
    }, []);
    
    async function retrievePlayers(teamId) {
      const response = await PlayerService.getAllInTeam(teamId);
      setPlayers(response.data);
    }

    const openRotationMode = (player) => {
      console.log(player.id);
      setDisplaySwapContainer(true);

      setPlayerForRotation({
        id: player.id,
        positionIndex: player.positionIndex,
        firstName: player.firstName,
        lastName: player.lastName,
        dob: player.dob,
        price: player.price,
        defaultPosition: player.defaultPosition,
        currentPosition: player.currentPosition,
        kitNr: player.kitNr,
        playerStats: player.playerStats,
        team: player.team,
        starting: player.starting
      });
    }

    const closeSwapContainer = () => {
      setDisplaySwapContainer(false);
    }

    const handlePlayersRotation = (playerToSwap, playerToRotateWith) => {
      const playerForRotationData = {
        id: playerToSwap.id,
        positionIndex: playerToRotateWith.positionIndex,
        firstName: playerToSwap.firstName,
        lastName: playerToSwap.lastName,
        dob: playerToSwap.dob,
        price: playerToSwap.price,
        defaultPosition: playerToSwap.defaultPosition,
        currentPosition: playerToRotateWith.currentPosition,
        kitNr: playerToSwap.kitNr,
        playerStats: playerToSwap.playerStats,
        team: playerToSwap.team,
        starting: playerToSwap.starting
      }

      const playerToRotateWithData = {
          id: playerToRotateWith.id,
          positionIndex: playerToSwap.positionIndex,
          firstName: playerToRotateWith.firstName,
          lastName: playerToRotateWith.lastName,
          dob: playerToRotateWith.dob,
          price: playerToRotateWith.price,
          defaultPosition: playerToRotateWith.defaultPosition,
          currentPosition: playerToSwap.currentPosition,
          kitNr: playerToRotateWith.kitNr,
          playerStats: playerToRotateWith.playerStats,
          team: playerToRotateWith.team,
          starting: playerToRotateWith.starting
      }


      PlayerService.update(playerForRotationData)
      .then(response => {
        setOrigin({
          id: response.data.id,
          positionIndex: response.data.positionIndex,
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          dob: response.data.dob,
          price: response.data.price,
          defaultPosition: response.data.defaultPosition,
          currentPosition: response.data.currentPosition,
          kitNr: response.data.kitNr,
          playerStats: response.data.playerStats,
          team: response.data.team,
          starting: response.data.starting
        })
      })
      .catch(e => {
        console.log(e);
      });

      PlayerService.update(playerToRotateWithData)
      .then(response => {
        setDest({
          id: response.data.id,
          positionIndex: response.data.positionIndex,
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          dob: response.data.dob,
          price: response.data.price,
          defaultPosition: response.data.defaultPosition,
          currentPosition: response.data.currentPosition,
          kitNr: response.data.kitNr,
          playerStats: response.data.playerStats,
          team: response.data.team,
          starting: response.data.starting
        })
      })
      .catch(e => {
        console.log(e);
      });

      const newStartingPlayersState = players.map((p) => {
        if (p.id === playerToSwap.id) {
          p.positionIndex = playerToRotateWith.positionIndex;
        }
        if (p.id === playerToRotateWith.id) {
          p.positionIndex = playerToSwap.positionIndex;
        }
        return p;
      });
      setPlayers(newStartingPlayersState);

    }

    const click1 = () => {
      const opponentsList = players.map((player) => {
        return player;
      
      });
    
      console.log(opponentsList[0].positionIndex);
    //return opponentsList[index];
    }
    
    return (
        <>
        {!displaySwapContainer ? 
        (<>
        <h1>Team: {team.name}</h1>
        <h3>Manager: {team.manager.nickname}</h3>
        <h3>Formation: {team.formation.name}</h3>
        <h3 onClick={click1}>Click</h3>
        
        <div className="team-squad">
        <Container className="startingTeam">
          <Row>
            {players && players.filter(player => player.starting == true && player.positionIndex >= 1 && player.positionIndex <= 3).sort((a, b) => a.positionIndex - b.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.starting == true && player.positionIndex >= 4 && player.positionIndex <= 6).sort((a, b) => a.positionIndex - b.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.starting == true && player.positionIndex >= 7 && player.positionIndex <= 9).sort((a, b) => a.positionIndex - b.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.starting == true && player.positionIndex >= 10 && player.positionIndex <= 12).sort((a, b) => a.positionIndex - b.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>

        </Container>
        {/* <div className="starting-players-pitch">
            {players && players.filter(player => player.starting == true).sort((a, b) => a.positionIndex - b.positionIndex).map((player, index) => (
              <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              
            ))}
        </div> */}
        <div className="reserves">
            {players && players.filter(player => player.starting == false).map((player) => (
              <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
            ))}
        </div>
        {/* <ReservesPanel/> */}
        </div>
        </>)
        :
        (<SwapPlayerContainer playerToSwap={playerForRotation} closeSwapContainer={closeSwapContainer} handlePlayersRotation={handlePlayersRotation}/>)
        }
        </>
    );
}

export default StartingPlayersContainer;