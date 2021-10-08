import React, {useState, useEffect} from 'react';
import Player from "./Player";
import { useDrop } from "react-dnd";
import FormationPositionBox from './FormationPositionBox';
import FormationPositionService from '../services/FormationPositionService';
import ReservesPanel from './ReservesPanel';


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

    const [formationPosBoxesStarting, setFormationPosBoxesStarting] = useState();
    const [formationPosBoxesReserves, setFormationPosBoxesReserves] = useState();
    

    

    useEffect(() => {

        const createdTeam = localStorage.getItem("team");
       
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          retrieveFormationPositionsStarting(foundTeam.id, foundTeam.formation.id);
          retrieveFormationPositionsReserves(foundTeam.id, foundTeam.formation.id);
        } 
    }, []);
    
    
    
    const retrieveFormationPositionsStarting = (teamId, formationId) => {
        FormationPositionService.getStartingPositionsByTeamAndFormation(teamId, formationId)
          .then(response => {
            setFormationPosBoxesStarting(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    };

    const retrieveFormationPositionsReserves = (teamId, formationId) => {
        FormationPositionService.getPositionsForReservesByTeamAndFormation(teamId, formationId)
          .then(response => {
            setFormationPosBoxesReserves(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    };

    const [swapStart, setSwapStart] = useState(null);
    const [swapDest, setSwapDest] = useState(null); 

    const handleSwap = (positionId) => {
      console.log(positionId);
      if(swapStart === null)
      {
        setSwapStart(positionId);
      }
      else{
        if(swapStart === positionId){
          setSwapStart(null);
          alert("players swapping canceled!");
        }
        else{
          setSwapDest(positionId);
          

          // act

          //clear
          setSwapStart(null);
          setSwapDest(null);
        }
      }

      

      
    }


    return (
        <>
        <h3>{swapStart} {swapDest}</h3>
        <h1>Team: {team.name}</h1>
        <h3>Manager: {team.manager.nickname}</h3>
        <h3>Formation: {team.formation.name}</h3>
        <div className="team-squad">
        <div className="starting-players-pitch">
            {formationPosBoxesStarting && formationPosBoxesStarting.map((position) => (
                <FormationPositionBox playerId={position.player!== null? position.player.id: null} 
                key={position.id} positionId={position.id} handleSwap={handleSwap}/>
            ))}
        </div>
        <div className="reserves">
            {formationPosBoxesReserves && formationPosBoxesReserves.map((position) => (
                <FormationPositionBox playerId={position.player!== null? position.player.id: null} 
                key={position.id} positionId={position.id} handleSwap={handleSwap} />
            ))} 
        </div>
        {/* <ReservesPanel/> */}
        </div>
        </>
    );
}

export default StartingPlayersContainer;