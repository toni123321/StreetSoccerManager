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


    const initialPositionState = {
        id: null,
        name: "",
        formation:null,
        team:null,
        x_cor:null,
        y_cor:null,
        player:null
    }
    const [swapOrigin, setSwapOrigin] = useState(initialPositionState);
    const [swapDest, setSwapDest] = useState(initialPositionState);
    


    
    // // const [swapStart, setSwapStart] = useState(initialPositionState);
    // // const [swapDest, setSwapDest] = useState(initialPositionState); 


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


    const getSwapOrigin = (id) => {
      FormationPositionService.get(id)
            .then(response => {
              setSwapOrigin({
                id: response.data.id,
                name: response.data.name,
                formation:response.data.formation,
                team:response.data.team,
                x_cor:response.data.x_cor,
                y_cor:response.data.y_cor,
                player:response.data.player
              });
            })
            .catch(e => {
              console.log(e);
            });
    }

    const getSwapDest = (id) => {
      FormationPositionService.get(id)
            .then(response => {
              setSwapDest({
                id: response.data.id,
                name: response.data.name,
                formation:response.data.formation,
                team:response.data.team,
                x_cor:response.data.x_cor,
                y_cor:response.data.y_cor,
                player:response.data.player
              });
            })
            .catch(e => {
              console.log(e);
            });
    }


    // const handleClicked = (e) => {
    //   console.log(e.target.id);
    //   if(swapOrigin.id === null)
    //   {
    //     getSwapOrigin(e.target.id);
    //   }
    //   else{
    //     if(swapOrigin.id !== e.target.id){
    //       getSwapDest(e.target.id);

    //       // setSwapOrigin({...swapOrigin, player: swapDest.player});
          
    //     }
    //     else{
    //       setSwapOrigin(initialPositionState);
    //     }
    //   }
    // }
  
    
    const swapPlayers = (currPos) => {
      
      setSwapOrigin(currPos);
      // const data = {
      //   id: null,
      //   name: "",
      //   formation:null,
      //   team:null,
      //   x_cor:null,
      //   y_cor:null,
      //   player:null
      // }
      // FormationPositionService.update(currPos.id, data)
      //       .then(response => {
      //         setSwapOrigin({
      //           id: response.data.id,
      //           name: response.data.name,
      //           formation:response.data.formation,
      //           team:response.data.team,
      //           x_cor:response.data.x_cor,
      //           y_cor:response.data.y_cor,
      //           player:response.data.player
      //         });
      //       })
      //       .catch(e => {
      //         console.log(e);
      //       });

      
      

    }
    
    return (
        <>
        <h1>Team: {team.name}</h1>
        <h3>Manager: {team.manager.nickname}</h3>
        <h3>Formation: {team.formation.name}</h3>
        <div className="team-squad">
        <div className="starting-players-pitch">
            {formationPosBoxesStarting && formationPosBoxesStarting.map((position) => (
                <FormationPositionBox playerId={position.player!== null? position.player.id: null} 
                key={position.id} positionId={position.id} swapPlayers={swapPlayers}/>
            ))}
        </div>
        <div className="reserves">
            {formationPosBoxesReserves && formationPosBoxesReserves.map((position) => (
              <FormationPositionBox playerId={position.player!== null? position.player.id: null} 
                key={position.id} positionId={position.id} swapPlayers={swapPlayers}/>
              
            ))} 
        </div>
        {/* <ReservesPanel/> */}
        </div>
        </>
    );
}

export default StartingPlayersContainer;