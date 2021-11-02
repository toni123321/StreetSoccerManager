import React, {useState} from "react";
import { Link } from 'react-router-dom';

function PlayerAvailableForRotation({player, rotationMode, handleRotation}) {
  

  const openRotationPlayerContainer = () => {
    // save player id in local storage
    localStorage.setItem('playerForRotationId', player.id);
  }

  const handleChosenPlayerForRotation = () => {
    console.log("chosen for rotation", player.id);
    //getPlayersForRotation(player);
    handleRotation(player);

  }
 
  return (
    <div className="player" onClick={rotationMode == true ? handleChosenPlayerForRotation : undefined}>
        {/* <span className="kitNr">{player.kitNr}</span> */}
        
        {/* <div className="overallRating">{player.playerStats.overallRating}</div> */}
        
        {rotationMode != true ?
        (
          <Link to='/rotatePlayers'>
            <button onClick={openRotationPlayerContainer}><i className="fas fa-exchange-alt"></i></button>
          </Link>
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

export default PlayerAvailableForRotation;