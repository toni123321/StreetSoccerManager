import React, {useState} from "react";
import { useDrag } from "react-dnd";

function Player({player, position, handleSwap}) {
  
  return (
    <div className="player">
        {/* <span className="kitNr">{player.kitNr}</span> */}
        
        {/* <div className="overallRating">{player.playerStats.overallRating}</div> */}
        
        <div>
          <span className="fa-stack fa-2x">
            <i className="fas fa-tshirt fa-stack-2x"></i>
            <strong className="fa-stack-1x shirt-text">{player.kitNr}</strong>
          </span>
        </div>
        <div>{player.firstName[0]}.{player.lastName}</div>
        <hr/>
        
        <div>{position.name} {player.playerStats.overallRating}</div>
       
        
        
    </div>
  );
}

export default Player;