import React, {useEffect, useState} from 'react';
import PlayerService from '../services/PlayerService';
import Player from './Player';
import { useHistory } from 'react-router';
import PlayerPositionInfoService from '../services/PlayerPositionInfoService';
import PlayerAvailableForRotation from './PlayerAvailableForRotation';

function RotatePlayersContainer() {
    let history = useHistory();
    const playerInitialState = {
        id: null,
        playerPersonalInfo: {
            id: null,
            firstName: "",
            lastName: ""
        },
        playerPositionInfo: {
            id: null,
            positionIndex: null,
            defaultPosition: null,
            currentPosition: null,
            starting: true
        },
        playerTeamInfo: {
            id: null,
            team: {
                id:null
            }
        },
        playerAdditionalInfo: null
    }

    const [playerForRotation, setPlayerForRotation] = useState(playerInitialState);
    const [playersAvailableForRotation, setPlayersAvailableForRotation] = useState(null);

    useEffect(() => {
        const playerForRotationId = localStorage.getItem("playerForRotationId");
        if (playerForRotationId) {
          retrievePlayer(playerForRotationId);
        } 
    }, []);

    async function retrievePlayer(id) {
        const response = await PlayerService.get(id);
        setPlayerForRotation(response.data);
        retrievePlayersAvailableForRotation(response.data.playerTeamInfo.team.id, response.data.id);
    }

    const retrievePlayersAvailableForRotation = (teamId, playerToSwapId) => {
        PlayerService.getAllInTeamAvailableForSwapping(teamId, playerToSwapId)
        .then(response => {
          setPlayersAvailableForRotation(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }

    const closeRotationContainer = () => {
        history.push('/teamSquad');
    }

    const handleRotation = (player) => {
        console.log(playerForRotation.id, player.id);
        
        const playerForRotationPositionData = {
            id: playerForRotation.playerPositionInfo.id,
            positionIndex: player.playerPositionInfo.positionIndex,
            defaultPosition: playerForRotation.playerPositionInfo.defaultPosition,
            currentPosition:  playerForRotation.playerPositionInfo.currentPosition,
            starting: playerForRotation.playerPositionInfo.starting
          }

        const playerToRotateWithPositionData = {
            id: player.playerPositionInfo.id,
            positionIndex: playerForRotation.playerPositionInfo.positionIndex,
            defaultPosition:player.playerPositionInfo.defaultPosition ,
            currentPosition: player.playerPositionInfo.currentPosition,
            starting: player.playerPositionInfo.starting
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
        
       // history.push('/teamSquad');
        
    }


    return (
        <>
            <div>
                {playerForRotation.id} {playerForRotation.playerPersonalInfo.firstName[0]}.{playerForRotation.playerPersonalInfo.lastName}
            </div>
            <button onClick={closeRotationContainer}><i className="fas fa-times"></i></button>

            {/* Container with available for swapping players */}
            <div className="availableForSwapping">
                {playersAvailableForRotation && playersAvailableForRotation.map((player) => (
                    <Player key={player.id} player={player} rotationMode={true} handleRotation={handleRotation}
                    />
                ))}
            </div>


        </>
    );
}

export default RotatePlayersContainer;