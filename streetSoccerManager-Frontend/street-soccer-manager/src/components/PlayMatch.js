import React, {useState, useEffect} from 'react';

function PlayMatch({chooseNewOpponent, chosenOpponent, homeTeam, awayTeam}) {
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
    const [userTeam, setUserTeam] = useState(initialTeamState);

    useEffect(() => {
        
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
            const foundTeam = JSON.parse(createdTeam);
            setUserTeam(foundTeam);
        } 
        
    }, [])
    const goBack = () => {
        chooseNewOpponent();
    }
    return (
        <>
        <div className="teams">
            {homeTeam.name} vs {awayTeam.name}
        </div>
        <button className="playMatch"><i class="fas fa-futbol"></i> Play match <i class="fas fa-futbol"></i></button>
        <button className="goBack" onClick={goBack}><i class="fas fa-hand-point-left"></i> Go back</button>
        </>
    );
}

export default PlayMatch;