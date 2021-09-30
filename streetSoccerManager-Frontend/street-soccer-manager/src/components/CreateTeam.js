import React from 'react';
import {useState} from "react";
import TeamService from "../services/TeamService";
import styles from "../css/CreateTeam.css";
import CreateTeamForm from './CreateTeamForm';

function CreateTeam() {

    const initialTeamState = {
        id: null,
        name: "",
        formation: "",
        manager: null
    }
    const [team, setTeam] = useState(initialTeamState);

    const [submitted, setSubmitted] = useState(false);


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
                   formation: response.data.formation,
                   manager: response.data.manager
                });
               setSubmitted(true);
               console.log(response.data);              
            })
            .catch(e => { console.log(e)});
        console.log(team.name);
    };

    const newTeam = () => {
        setTeam(initialTeamState);
        setSubmitted(false);
    };

    return (
        <div>
            {submitted ? 
            (
                <>
                <div>Team created</div>
                <h2>Team name: {team.name}</h2>
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