import React from 'react';
import {useState} from "react";

const CreateTeamForm = ({createTeam}) => {
    const [name, setName] = useState("");

    const handleChange = (e) => {
        setName(e.target.value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        createTeam(name);
        console.log("new team created");
        console.log(name);
        
        
    }


    return(
        <form id="createTeam" onSubmit={handleSubmit}>
            <h2>Create Team</h2>
            <input value={name} placeholder="team name ..." type="text" name="name" onChange={handleChange}></input><br/>
            <button type="submit" className="bn632-hover bn26">Create team</button>
        </form>
    );
}

export default CreateTeamForm;