import React, {useState, useEffect} from 'react';
import Player from "./Player";
import { useDrop } from "react-dnd";
import FormationPositionBox from './FormationPositionBox';



const PlayersList = [
    {
      id: 1,
      index: 1,
    },
    {
      id: 2,
      index: 2,
    },
];

const StartingPlayersContainer = () => {
    const initialTeamState = {
        id: null,
        name: "",
        formation: {
            id:null,
            name: ""
        },
        manager: null
    }

    const [team, setTeam] = useState(initialTeamState);
    
    useEffect(() => {

        const createdTeam = localStorage.getItem("team");
        const createdFormation = localStorage.getItem("team-formation");
       
        if (createdTeam || createdFormation) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);

          
        } 
      }, []);

    const [index, setIndex] = useState(1);

    function movePlayer(i) {
        setIndex(i);
    }
    return (
        <>
        <h1>{team.name}</h1>
        <h2>Formation: {team.formation.name}</h2>
        <div className="starting-players-pitch">
            
            <FormationPositionBox player={index === 1} movePlayer={movePlayer.bind(null, 1)} />
            <FormationPositionBox player={index === 2} movePlayer={movePlayer.bind(null, 2)}/>
            <FormationPositionBox player={index === 3} movePlayer={movePlayer.bind(null, 3)}/>
        </div>
        </>
    );
}

export default StartingPlayersContainer;