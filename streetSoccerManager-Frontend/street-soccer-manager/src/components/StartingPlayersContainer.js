import React, {useState, useEffect} from 'react';
import Player from "./Player";
import { useDrop } from "react-dnd";
import FormationPositionBox from './FormationPositionBox';
import FormationPositionService from '../services/FormationPositionService';
import ReservesPanel from './ReservesPanel';
import PlayerService from '../services/PlayerService';
import SwapPlayerContainer from './SwapPlayerContainer';


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
  
    const [team, setTeam] = useState(initialTeamState);

    const [startingPlayers, setStartingPlayers] = useState([]);
    const [reserves, setReserves] = useState([]);

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

    
    const [playerToSwap, setPlayerToSwap] = useState(playerInitialState);
    const [swapDest, setSwapDest] = useState(playerInitialState);

    const [origin, setOrigin] = useState(playerInitialState);
    const [dest, setDest] = useState(playerInitialState);
    
    const [displaySwapContainer, setDisplaySwapContainer] = useState(false);

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          console.log("here");
          retrieveStartingPlayers(foundTeam.id);
          retrieveReserves(foundTeam.id);
        } 
    }, []);
    
    const retrieveStartingPlayers = (teamId) => {
      PlayerService.getStartingPlayers(teamId)
        .then(response => {
          setStartingPlayers(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }

    const retrieveReserves = (teamId) => {
      PlayerService.getReserves(teamId)
        .then(response => {
          setReserves(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }


    const handleSwap = (player) => {
      console.log(player.id);
      setDisplaySwapContainer(true);

      setPlayerToSwap({
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

      const newStartingPlayersState = startingPlayers.map((p) => {
        if (p.id === playerToSwap.id) {
          p.positionIndex = playerToRotateWith.positionIndex;
        }
        if (p.id === playerToRotateWith.id) {
          p.positionIndex = playerToSwap.positionIndex;
        }
        return p;
      });
      setStartingPlayers(newStartingPlayersState);
      
    }

    return (
        <>
        {!displaySwapContainer ? 
        (<>
        <h1>Team: {team.name}</h1>
        <h3>Manager: {team.manager.nickname}</h3>
        <h3>Formation: {team.formation.name}</h3>
        
        <div className="team-squad">
        <div className="starting-players-pitch">
            {startingPlayers && startingPlayers.sort((a, b) => a.positionIndex - b.positionIndex).map((player) => (
              <Player key={player.id} player={player} handleSwap={handleSwap}/>
            ))}
        </div>
        <div className="reserves">
            {reserves && reserves.map((player) => (
              <Player key={player.id} player={player} handleSwap={handleSwap}/>
            ))}
        </div>
        {/* <ReservesPanel/> */}
        </div>
        </>)
        :
        (<SwapPlayerContainer playerToSwap={playerToSwap} closeSwapContainer={closeSwapContainer} handlePlayersRotation={handlePlayersRotation}/>)
        }
        </>
    );
}

export default StartingPlayersContainer;