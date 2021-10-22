import React, {useState} from "react";
import { useDrag } from "react-dnd";

function Player({player, openRotationMode, getPlayersForRotation}) {
  

  const handleClick = () => {
    openRotationMode(player);
  }

  const handleChosenPlayerForRotation = () => {
    console.log("chosen for rotation", player.id);
    getPlayersForRotation(player);
  }

  return (
    <div className="player" onClick={openRotationMode == null ? handleChosenPlayerForRotation : undefined}>
        {/* <span className="kitNr">{player.kitNr}</span> */}
        
        {/* <div className="overallRating">{player.playerStats.overallRating}</div> */}
        
        {openRotationMode != null ?
        (<button onClick={handleClick}><i className="fas fa-exchange-alt"></i></button>)
        :
        (<></>)
        }
        <div>
          <span className="fa-stack fa-2x">
            <i className="fas fa-tshirt fa-stack-2x"></i>
            <strong className="fa-stack-1x shirt-text">{player.kitNr}</strong>
          </span>
        </div>
        <div>{player.firstName[0]}.{player.lastName}</div>
        <hr/>
        <div>{player.positionIndex}</div>
        <div>{player.currentPosition.position} {player.playerStats.overallRating}</div>
       
        
        
    </div>
  );
}

export default Player;