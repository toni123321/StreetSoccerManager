import React, {useState, useEffect} from 'react';
import FriendlyMatchService from '../../../services/FriendlyMatchService';
import  "../../../css/playMatch.scss";
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
    const [matchStarted, setMatchStarted] = useState(false);
    const [actionInProgress, setActionInProgress] = useState(false);
    const [action, setAction] = useState("");
    const [currMatchId, setCurrMatchId] = useState(null);
    const [homeTeamGoals, setHomeTeamGoals] = useState(0);
    const [awayTeamGoals, setAwayTeamGoals] = useState(0);

    const [matchEnd, setMatchEnd] = useState(false);
    const [actionError, setActionError] = useState(false);
    

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
        const matchInfoState = {
            homeTeam: matchInfo.homeTeam,
            awayTeam: matchInfo.awayTeam
        }
        FriendlyMatchService.startMatch(matchInfoState, token).
        then(response => {
            setMatchStarted(true);
            setCurrMatchId(response.data.id);
            let result = response.data.result;
            
            setHomeTeamGoals(result.substring(0, 1));
            setAwayTeamGoals(result.substring(2));
        })
        .catch(e=>{
            console.log(e);
        });
    }

    function playMatch(command) {
        setActionInProgress(true);
        console.log(command);
        
        const playMatchState = {
            id: currMatchId,
            command: command 
        }

        
        FriendlyMatchService.playMatch(playMatchState, token)
        .then(response => {
            setMatchStarted(true);
            setCurrMatchId(response.data.id);

            let result = response.data.result;
            
            setHomeTeamGoals(result.substring(0, 1));
            setAwayTeamGoals(result.substring(2));

            if(command === "ATTACK"){
                setAction("Your turn. Your team is attacking ...");
            }
            else{
                setAction("Your turn. Your team is defencing ... ")
            }

            setTimeout(function() {
                setMatchEnd(response.data.isMatchEnd);
            }, 2000);
            
        })
        .catch(function (error) {
            if (error.response) {
              console.log(error.response.status);
              setMatchEnd(true);
            }
            else{
               setActionError(true);
            }
        });

        setTimeout(function() {
            setActionInProgress(false);
        }, 2000);
    }

    const handleGoToGameMenu = () => {
        history.push("/game");
    }

    const handlePlayAgain = () => {
        chooseNewOpponent();
    }

    return (
        <>
        {matchStarted === false
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
                matchEnd === false ?
                (
                <div className="matchStarted">
                    <h2>Match started</h2>
                    <div className="matchResult">
                        <div className="team-oppponents matchResultItem">{homeTeam.name}</div>
                        <div className="team-vs matchResultItem"><span>{homeTeamGoals}</span><span className='result-divider'>:</span><span>{awayTeamGoals}</span></div>
                        <div className="team-oppponents matchResultItem">{awayTeam.name}</div>
                    </div>
                    {!actionInProgress ? 
                    (
                        <div className="matchActiveSimulation">
                            <button className="play-match-attack-btn" onClick={() => playMatch("ATTACK")}>ATTACK</button>
                            <button className="play-match-def-btn" onClick={() => playMatch("DEF")}>DEFENCE</button>
                                
                        </div>
                    ):
                    (
                        <div className="play-match-action">{action}</div>
                    )
                    }
                    
                </div>
                ):
                (
                    <div className="matchEnd">
                        {/* <div className="match-result"></div>
                        <div className="match-statistic">{matchReview.statistic}</div> */}
                        <h2>Match ends</h2>
                        <div className="matchResult">
                            <div className="team-oppponents matchResultItem">{homeTeam.name}</div>
                            <div className="team-vs matchResultItem"><span>{homeTeamGoals}</span><span className='result-divider'>:</span><span>{awayTeamGoals}</span></div>
                            <div className="team-oppponents matchResultItem">{awayTeam.name}</div>
                        </div>
                        <div className="play-match-navigation">
                        <button className="goBack" onClick={handleGoToGameMenu}>Back to Game menu</button>
                        <button className="playAgain" onClick={handlePlayAgain}>Play again</button>
                        </div>
                    </div>
                )
            )
        }
        </>
        
    );
}

export default PlayMatch;