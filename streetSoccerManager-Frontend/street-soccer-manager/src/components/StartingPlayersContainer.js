import React, {useState, useEffect} from 'react';
import Player from "./Player";
import { useDrop } from "react-dnd";
import FormationPositionBox from './FormationPositionBox';
import FormationPositionService from '../services/FormationPositionService';
import ReservesPanel from './ReservesPanel';
import PlayerService from '../services/PlayerService';
import SwapPlayerContainer from './SwapPlayerContainer';
import {Container, Col, Row} from 'react-bootstrap';
import PlayerPositionInfoService from '../services/PlayerPositionInfoService';


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
      playerPersonalInfo: null,
      playerPositionInfo: {
        id: null,
        positionIndex: null,
        defaultPosition: null,
        currentPosition: null,
        starting: true
      },
      playerTeamInfo: null,
      playerAdditionalInfo: null
    }
  
    const [team, setTeam] = useState(initialTeamState);
    const [players, setPlayers] = useState([]); 

    const [playerForRotation, setPlayerForRotation] = useState(playerInitialState);
    const [swapDest, setSwapDest] = useState(playerInitialState);

    const [origin, setOrigin] = useState(playerInitialState);
    const [dest, setDest] = useState(playerInitialState);
    
    const [displaySwapContainer, setDisplaySwapContainer] = useState(false);
    const [newChange, setNewChange] = useState(true);
    const [value, setValue] = useState();

    useEffect(() => {
      if(newChange === true)
      {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          console.log("here");
          //retrieveStartingPlayers(foundTeam.id);
          //retrieveReserves(foundTeam.id);
          retrievePlayers(foundTeam.id);
          
        } 
      }
      setNewChange(false);
    }, [newChange]);
    
    async function retrievePlayers(teamId) {
      const response = await PlayerService.getAllInTeam(teamId);
      setPlayers(response.data);
    }

    async function retrievePlayer(id) {
      const response = await PlayerService.get(id);
      setPlayers([...players], response);
    }

    const openRotationMode = (player) => {
      console.log(player.id);
      setDisplaySwapContainer(true);

      setPlayerForRotation({
        id: player.id,
        playerPersonalInfo: player.playerPersonalInfo,
        playerPositionInfo: player.playerPositionInfo,
        playerTeamInfo: player.playerTeamInfo,
        playerAdditionalInfo: player.playerAdditionalInfo
      });
    }

    const closeSwapContainer = () => {
      setDisplaySwapContainer(false);
    }

    const handlePlayersRotation = (playerToSwap, playerToRotateWith) => {

      const playerForRotationPositionData = {
        id: playerToSwap.playerPositionInfo.id,
        positionIndex: playerToRotateWith.playerPositionInfo.positionIndex,
        defaultPosition: playerToSwap.playerPositionInfo.defaultPosition,
        currentPosition:  playerToSwap.playerPositionInfo.currentPosition,
        starting: playerToSwap.playerPositionInfo.starting
      }

      console.log(playerForRotationPositionData);
      const playerToRotateWithPositionData = {
        id: playerToRotateWith.playerPositionInfo.id,
        positionIndex: playerToSwap.playerPositionInfo.positionIndex,
        defaultPosition:playerToRotateWith.playerPositionInfo.defaultPosition ,
        currentPosition: playerToRotateWith.playerPositionInfo.currentPosition,
        starting: playerToRotateWith.playerPositionInfo.starting
      }
      console.log(playerToRotateWithPositionData);

      PlayerPositionInfoService.update(playerForRotationPositionData)
      .then(response => {
        console.log(response.data.positionIndex);
      })
      .catch(e => {
        console.log(e);
      });

      PlayerPositionInfoService.update(playerToRotateWithPositionData)
      .then(response => {
        console.log(response.data.positionIndex);
      })
      .catch(e => {
        console.log(e);
      });

      
      console.log(playerToSwap.id, playerToRotateWith.id);
      
      // const newStartingPlayersState = players.map((p) => {
      //   if (p.id === playerToSwap.id) {
      //     p.playerPositionInfo.positionIndex = playerToSwap.playerPositionInfo.positionIndex;
      //   }
      //   console.log(playerToRotateWith.id);
      //   if (p.id === playerToRotateWith.id) {
      //     p.playerPositionInfo.positionIndex = playerToRotateWith.playerPositionInfo.positionIndex;
      //   }
      //   return p;
      // });
      // setPlayers(newStartingPlayersState);
      // retrievePlayers(team.id);
      //retrievePlayer(playerToSwap.id);
      //retrievePlayer(playerToRotateWith.id);

      setNewChange(true);
      
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
            {players && players.filter(player => player.playerPositionInfo.starting == true && player.playerPositionInfo.positionIndex >= 1 && player.playerPositionInfo.positionIndex <= 3).sort((a, b) => a.playerPositionInfo.positionIndex - b.playerPositionInfo.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.playerPositionInfo.starting == true && player.playerPositionInfo.positionIndex >= 4 && player.playerPositionInfo.positionIndex <= 6).sort((a, b) => a.playerPositionInfo.positionIndex - b.playerPositionInfo.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.playerPositionInfo.starting == true && player.playerPositionInfo.positionIndex >= 7 && player.playerPositionInfo.positionIndex <= 9).sort((a, b) => a.playerPositionInfo.positionIndex - b.playerPositionInfo.positionIndex).map((player, index) => (
              <Col>
               <Player key={player.id} player={player} openRotationMode={openRotationMode}/>
              </Col>
            ))}
          </Row>
          <Row>
            {players && players.filter(player => player.playerPositionInfo.starting == true && player.playerPositionInfo.positionIndex >= 10 && player.playerPositionInfo.positionIndex <= 12).sort((a, b) => a.playerPositionInfo.positionIndex - b.playerPositionInfo.positionIndex).map((player, index) => (
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
            {players && players.filter(player => player.playerPositionInfo.starting == false).map((player) => (
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