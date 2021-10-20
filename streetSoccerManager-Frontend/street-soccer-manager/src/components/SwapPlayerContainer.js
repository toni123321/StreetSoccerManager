import React, {useState, useEffect} from 'react';
import PlayerService from '../services/PlayerService';
import Player from './Player';

const SwapPlayerContainer = ({playerToSwap, closeSwapContainer, handlePlayersRotation}) => {
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
    const [playersAvailableForSwap, setPlayersAvailableForSwap] = useState(null);
    const [origin, setOrigin] = useState(playerInitialState);
    const [dest, setDest] = useState(playerInitialState);
    

    const retrievePlayersAvailableForSwap = (teamId, playerToSwapId) => {
        PlayerService.getAllInTeamAvailableForSwapping(teamId, playerToSwapId)
        .then(response => {
          setPlayersAvailableForSwap(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }

    useEffect(() => {
        retrievePlayersAvailableForSwap(playerToSwap.team.id, playerToSwap.id);
    }, [])


    const handleClick = () => {
        closeSwapContainer();
    }

    const getPlayersForRotation = (playerToRotateWith) => {
        console.log("Player to rotate with", playerToRotateWith.id);
        // send put request to update the two players indexes
        let playerForRotationIndex = playerToSwap.positionIndex;
        let playerToRotateWithIndex = playerToRotateWith.positionIndex;
        console.log(playerForRotationIndex, playerToRotateWithIndex);
        handlePlayersRotation(playerToSwap, playerToRotateWith);
        closeSwapContainer();
    }
    
    return (
        <>
            <div>
                {playerToSwap.id} {playerToSwap.firstName[0]}.{playerToSwap.lastName}
            </div>
            <button onClick={handleClick}><i className="fas fa-times"></i></button>

            {/* Container with available for swapping players */}
            <div className="availableForSwapping">
                {playersAvailableForSwap && playersAvailableForSwap.map((player) => (
                    
                    <Player key={player.id} player={player} 
                    getPlayersForRotation={getPlayersForRotation}/>
                ))}
            </div>
        </>
    );
}

export default SwapPlayerContainer;