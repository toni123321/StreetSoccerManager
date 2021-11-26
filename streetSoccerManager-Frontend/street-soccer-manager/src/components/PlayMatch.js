import React, {useState, useEffect} from 'react';
import FriendlyMatchService from '../services/FriendlyMatchService';
import  "../css/playMatch.scss";
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router-dom';

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
        homeTeam: null,
        awayTeam: null
    }


    const [userTeam, setUserTeam] = useState(initialTeamState);
    const [matchInfo, setMatchInfo] = useState(initialMatchInfoState);
    const [matchEnd, setMatchEnd] = useState(false);
    const [matchReview, setMatchReview] = useState(null);


    const cookies = new Cookies();
    const token = cookies.get('login-token');
    let history = useHistory();

    useEffect(() => {
        
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
            const foundTeam = JSON.parse(createdTeam);
            setUserTeam(foundTeam);
            if(homeTeam.id == foundTeam.id)
            {
                setMatchInfo({
                    homeTeam: foundTeam,
                    awayTeam: chosenOpponent
                });
            }
            else{
                setMatchInfo({
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
        const matchInfoState = {
            homeTeam: matchInfo.homeTeam,
            awayTeam: matchInfo.awayTeam
        }
        const response = await FriendlyMatchService.playMatch(matchInfoState, token);
        setMatchReview(response.data);
        setMatchEnd(true);
    }

    const handleGoToGameMenu = () => {
        history.push("/game");
    }

    const handlePlayAgain = () => {
        chooseNewOpponent();
    }

    return (
        <>
        {matchEnd === false
            ?
            (
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
            )
            :
            (
                <div className="matchEnd">
                    
                    <div className="match-result">{matchReview.result}</div>
                    <div className="match-statistic">{matchReview.statistic}</div>
                    
                    <button className="goBack" onClick={handleGoToGameMenu}>Back to Game menu</button>
                    <button className="playAgain" onClick={handlePlayAgain}>Play again</button>
                </div>
            )
        }
        </>
        
    );
}

export default PlayMatch;