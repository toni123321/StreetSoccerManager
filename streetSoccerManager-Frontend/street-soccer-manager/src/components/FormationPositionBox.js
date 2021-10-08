import React, {useState, useEffect} from "react";
import { useDrop } from "react-dnd";
import Player from "./Player";
import FormationPositionService from "../services/FormationPositionService";
import PlayerService from "../services/PlayerService";

function FormationPositionBox({ playerId, positionId, swapPlayers}) {

    const initialPlayerState = {
      id: null,
      firstName: "",
      lastName: "",
      dob: "",
      price: null,
      defaultPosition: "",
      currentPosition: "",
      kitNr: null,
      isStarting: true,
      playerStats: null,
      team: null
    }
    const [currPlayer, setCurrPlayer] = useState(initialPlayerState);
    const [newPlayer, setNewPlayer] = useState(initialPlayerState); 

    const [currPos, setCurrPos] = useState({
      id: null,
      name: "",
      formation:null,
      team:null,
      x_cor:null,
      y_cor:null,
      player:null
    });

    const getCurrFormationPosition = (positionId) => {
      FormationPositionService.get(positionId)
          .then(response => {
            setCurrPos({
              id: response.data.id,
              name: response.data.name,
              formation: response.data.formation,
              team: response.data.team,
              x_cor: response.data.x_cor,
              y_cor: response.data.y_cor,
              player: response.data.player
            });
            if(response.data.player !== null){
              setCurrPlayer({
                id: response.data.player.id,
                firstName: response.data.player.firstName,
                lastName: response.data.player.lastName,
                dob: response.data.player.dob,
                price: response.data.player.price,
                defaultPosition: response.data.player.defaultPosition,
                currentPosition: response.data.player.currentPosition,
                kitNr: response.data.player.kitNr,
                isStarting: response.data.player.isStarting,
                playerStats: response.data.player.playerStats,
                team: response.data.player.team
              });
            }
          })
          .catch(e => {
            console.log(e);
      });
    }

    useEffect(() => {
      getCurrFormationPosition(positionId);
    }, [])

    const getNewPlayer = (id) => {
      PlayerService.get(id)
          .then(response => {
            setNewPlayer({
              id: response.data.id,
              firstName: response.data.firstName,
              lastName: response.data.lastName,
              dob: response.data.dob,
              price: response.data.price,
              defaultPosition: response.data.defaultPosition,
              currentPosition: response.data.currentPosition,
              kitNr: response.data.kitNr,
              isStarting: response.data.isStarting,
              playerStats: response.data.playerStats,
              team: response.data.team
            });
            
          })
          .catch(e => {
            console.log(e);
      });
    }
    
    // const handleClicked = () => {
    //   console.log(currPos.id);
      
    // }

    

    const handleClick = () => {
      //console.log(currPos.id);
      swapPlayers(currPos);

    }

    return (

      <div
        className="formationPosBox"
        
      >
        <button onClick={handleClick}><i class="fas fa-exchange-alt"></i></button>
        {currPlayer.id !== null ? <Player player={currPlayer} position={currPos}/> : "No player"}
      </div>
    );
  }

  export default FormationPositionBox;