import React, {useState, useEffect} from 'react';
import FriendlyMatchService from '../services/FriendlyMatchService';
import  "../css/playMatch.scss";

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
    const initialMatchInfoState = {
        id: null,
        homeTeam: {
            id:null,
            name: "",
            formation: {
                id: null,
                name: ""
            }
        },
        awayTeam: {
            id: null,
            name: "",
            formation: {
                id: null,
                name: ""
            }
        }
    }
    
    const [userTeam, setUserTeam] = useState(initialTeamState);
    const [matchInfo, setMatchInfo] = useState(initialMatchInfoState);

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
            const foundTeam = JSON.parse(createdTeam);
            setUserTeam(foundTeam);
            if(homeTeam.id == foundTeam.id)
            {
                setMatchInfo({
                    id: null,
                    homeTeam: foundTeam,
                    awayTeam: chosenOpponent
                });
            }
            else{
                setMatchInfo({
                    id: null,
                    homeTeam: chosenOpponent,
                    awayTeam: foundTeam
                });
            }
            
        }
        
    }, [])
    const goBack = () => {
        chooseNewOpponent();
    }

    const startMatch = () => {
        playFriendlyMatch();
    }

    async function playFriendlyMatch() {
        const response = await FriendlyMatchService.playMatch(matchInfo);
        setMatchInfo({...matchInfo, id:response.data.id});
        
    }

    return (
        <div className="startMatch">
        {/* <div>{matchInfo.homeTeam.name} vs {matchInfo.awayTeam.name}</div> */}
        <div className="teams">
            <span className="team-oppponents">{homeTeam.name}</span>
            <span className="team-vs">VS</span>
            <span className="team-oppponents">{awayTeam.name}</span>
        </div>
        <button className="goBack" onClick={goBack}>Go back</button>
        <button className="playMatch" onClick={startMatch}>Play match</button>
        </div>
    );
}

export default PlayMatch;