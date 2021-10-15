import React from 'react';
import {useState, useEffect} from "react";
import TeamService from "../services/TeamService";
import styles from "../css/CreateTeam.css";
import CreateTeamForm from './CreateTeamForm';
import GameNavbar from './GameNavbar';

import FormationService from '../services/FormationService';

function CreateTeam() {

    const initialTeamState = {
        id: null,
        name: "",
        formation: {
            id:null,
            name: ""
        },
        manager: "",
        user: null
    }
    const [team, setTeam] = useState(initialTeamState);

    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {

        const createdTeam = localStorage.getItem("team");
        console.log("Created team: ", createTeam);
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          setTeam(foundTeam);
          setSubmitted(true);
        } 
        else
        {
            const id = 0;
            TeamService.get(id)
                .then(response => {
                    setTeam({
                        id: response.data.id,
                        name: response.data.name,
                        formation: {
                            id: response.data.formation.id,
                            name: response.data.formation.name
                        },
                        manager: response.data.manager,
                        user: response.data.user
                     });
                    setSubmitted(true);
                    localStorage.setItem('team', JSON.stringify(response.data));
            });
        }
      }, []);

    const createTeam = (teamName) => {
        console.log("Team name:", teamName);
        const data = {
            name: teamName,
        }

        TeamService.create(data)
            .then(response => {
               setTeam({
                   id: response.data.id,
                   name: response.data.name,
                   formation: {
                       id: response.data.formation.id,
                       name: response.data.formation.name
                   },
                   manager: response.data.manager,
                   user: response.data.user
                });
               setSubmitted(true);
               console.log(response.data);   
               localStorage.setItem('team', JSON.stringify(response.data));

            })
            .catch(e => { console.log(e)});
        console.log(team.name);
        console.log("Created team: ", localStorage.getItem("team"));
        
    };

    const newTeam = () => {
        setTeam(initialTeamState);
        setSubmitted(false);
        localStorage.clear();
    };

    return (
        <div>
            {submitted ? 
            (
                <>
                <h2>Team name: {team.name}</h2>
                <GameNavbar team={team}/>
                <button onClick={newTeam}>
                    Add
                </button>
                </>
            ) 
            :
            (<CreateTeamForm createTeam={createTeam}/>)
            }
            
        </div>
    );
}

export default CreateTeam;