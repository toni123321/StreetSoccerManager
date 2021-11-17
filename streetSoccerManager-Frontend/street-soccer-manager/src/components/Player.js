import React, {useState} from "react";
import { Link } from 'react-router-dom';

function Player({player, rotationMode, handleRotation, changeRotationMode}) {
  

  const openRotationPlayerContainer = () => {
    // save player id in local storage
    localStorage.setItem('playerForRotationId', player.id);
    changeRotationMode();
  }

  const handleChosenPlayerForRotation = () => {
    handleRotation(player);
  }
 
  return (
    <div className="player" onClick={rotationMode == true ? handleChosenPlayerForRotation : undefined}>
        {rotationMode != true ?
        (
          <button onClick={openRotationPlayerContainer}><i className="fas fa-exchange-alt"></i></button>
        )
        :
        (<></>)
        }
        <div>
          <span className="fa-stack fa-2x">
            <i className="fas fa-tshirt fa-stack-2x"></i>
            <strong className="fa-stack-1x shirt-text">{player.playerTeamInfo.kitNr}</strong>
          </span>
        </div>
        <div>{player.playerPersonalInfo.firstName[0]}.{player.playerPersonalInfo.lastName}</div>
        <hr/>
        <div>{player.playerPositionInfo.positionIndex}</div>
        <div>{player.playerPositionInfo.currentPosition.position} {player.playerAdditionalInfo.playerStats.overallRating}</div>
       
        
        
    </div>
  );
}

export default Player;