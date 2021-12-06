import React, {useState, useEffect} from 'react';
import TeamService from '../services/TeamService';
import ChoosePlayersForFriendlyMatch from './ChoosePlayersForFriendlyMatch';
import Carousel from 'react-bootstrap/Carousel'
import PlayMatch from './PlayMatch';
import {Col, Row, Container} from 'react-bootstrap';
import "../css/chooseOpponent.scss";
import yourTeamLogo from "../resources/your-team-logo.png";
import opponentTeamLogo from "../resources/opponent-team-logo.png";
import TeamRating from './TeamRating';
import Cookies from 'universal-cookie';
import RatingService from '../services/RatingService';


function ChooseOpponent() {
    const cookies = new Cookies();
    const token = cookies.get('login-token');

    const [index, setIndex] = useState(0);

    const initialTeamState = {
        id: null,
        name: "",
        formation: null,
        manager:null
    }

    const initialTeamSideState = {
        id: null,
        name: ""
    }

    const [userTeam, setUserTeam] = useState(initialTeamState);

    const [homeTeam, setHomeTeam] = useState(initialTeamSideState);
    const [awayTeam, setAwayTeam] = useState(initialTeamSideState);

    const [chosenOpponent, setChosenOpponent] = useState({
        id: null,
        name: "",
        formation: null,
        managerName: ""
    });

    const [opponents, setOpponents] = useState([]);

    useEffect(() => {
        retrieveOpponents();
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
            const foundTeam = JSON.parse(createdTeam);
            console.log("Created team", foundTeam);
            setUserTeam(foundTeam);

            const homeTeamState = {
                id: foundTeam.id,
                name: foundTeam.name
            }
            setHomeTeam(homeTeamState);
        } 
    }, [])

    function retrieveTeamRating(teamId) {
        RatingService.getTeamRating(teamId, token)
        .then((response) => {
            return response.data;
        }, (error) => {
            console.log(error);
        });
    }

    function retrieveOpponents() {
        TeamService.getOfficialTeams(token)
        .then((response) => {
            setOpponents(response.data);
        }, (error) => {
            console.log(error);
        });
    }


    const [goNext, setGoNext] = useState(false);

    

    const getOpponent = (currIndex) => {
        
        let content = [];
        for (var i = 0; i < opponents.length; i++) {
            content.push(opponents[i]);
        }
        console.log(opponents);
    }
    
    const handleGoBack = () => {
        //console.log("go back!");
        if(index >= 1){
            setIndex(index - 1);
        }
    }

    const handleGoAhead = () => {
        //console.log("go ahead!");
        const opponentsList = opponents.map((opponent) => {
            return opponent;
        });

        if(index <= opponentsList.length -2) {
            setIndex(index + 1);
        }
        
    }

    const handleSelectOpponent = (opponent, isHomeTeam) => {
        //console.log(getOpponent(index).name);
        setChosenOpponent(opponent);
        const opponentTeamState = {
            id: opponent.id,
            name: opponent.name
        }
        isHomeTeam ? setHomeTeam(opponentTeamState): setAwayTeam(opponentTeamState);
    }

    const handleSaveData = () => {
        // save current data (localStorage or pass as props)
        console.log("Go to next step");
        if(chosenOpponent.id !== null)
        {
            // save home, away teams in localStorage
            
            setGoNext(true);
        }
        else{
            alert("You don't choose opponent!");
        }
    }

    const chooseNewOpponent = () => {
        setGoNext(false);
    }

    const click1 = () => {
        const opponentsList = opponents.map((player) => {
          return player;
        
        });
        console.log(opponentsList[index]);
    }

    const changeHomeAwayTeam = () => {
        const currHomeTeam = homeTeam;
        setHomeTeam(awayTeam);
        setAwayTeam(currHomeTeam);
    }


    return (
        <>
        {/* {opponents.map((o) => (
            
            <div>{o.name}</div>
            
        ))} */}
        {!goNext ? 
        (
            <>
            {/* {opponents.map((o) => 
                <div>{o.name}</div>
            )}

            <button onClick={click1}>Here</button>
            
            <button onClick={handleGoBack} className="search-left-opponents"><i class="fas fa-chevron-left"></i></button> */}
            {/* <span>{index}</span> */}
            {/* <span onClick={handleSelectOpponent}>{getOpponent(index)}</span>
            <button onClick={handleGoAhead} className="search-right-opponents"><i class="fas fa-chevron-right"></i></button> */}

            <div className="chooseOpponent">
                <Container>
                <Row className="justify-content-md-center">
                    <Col xs={12} md={5} className="homeTeam">
                        <h3 className="team-role">Home team</h3>
                        <div className="homeTeamName team">
                            {homeTeam.id === userTeam.id ?
                                (<div className="userTeam">
                                    <div className="team-name">{userTeam.name}</div>
                                    <img className="team-logo" src={yourTeamLogo}/>
                                    <TeamRating id={homeTeam.id}/>
                                    {console.log(homeTeam.id)}
                                </div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, true)}>
                                            <div className="teamToChoose">  
                                            <div className="team-name">{opponent.name}</div>
                                            <img className="team-logo" src={opponentTeamLogo}/>
                                            </div>
                                            <TeamRating id={opponent.id}/>
                                        </Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                            }
                            
                            
                            
                        </div>
                        {homeTeam.id === userTeam.id ?
                        (<h3 className="team-selected">Your team</h3>)
                        :
                        (
                        chosenOpponent.id === null ?
                        (<h3 className="team-selected">Not selected</h3>)
                        :
                        (<h3 className="team-selected">Selected - {chosenOpponent.name}</h3>)
                        )
                        }
                    </Col>
                    <Col className="changeSides mx-auto" xs={11} md={1}>
                    <div className="title-vs">VS</div>
                    <button onClick={changeHomeAwayTeam}><i class="fas fa-exchange-alt"></i></button>
                    </Col>
                    <Col xs={12} md={5} className="awayTeam">
                        <h3 className="team-role">Away team</h3>
                        <div className="awayTeamName team">
                        {awayTeam.id === userTeam.id ?
                                (<div className="userTeam">
                                    <div className="team-name">{userTeam.name}</div>
                                    <img className="team-logo" src={yourTeamLogo}/>
                                    <TeamRating id={awayTeam.id}/>
                                    
                                </div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, false)}>
                                            <div className="teamToChoose">  
                                            <div className="team-name">{opponent.name}</div>
                                            <img className="team-logo" src={opponentTeamLogo}/>
                                            </div>
                                            <TeamRating id={opponent.id}/>
                                        </Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                        }
                        
                        </div>
                        {awayTeam.id === userTeam.id ?
                        (<h3 className="team-selected">Your team</h3>)
                        :
                        (
                        chosenOpponent.id === null ?
                        (<h3 className="team-selected">Not selected</h3>)
                        :
                        (<h3 className="team-selected">Selected - {chosenOpponent.name}</h3>)
                        )
                        }
                    </Col>
                    
                </Row>
                </Container>
            </div>
            
            {/* <div>Chosen opponent: {chosenOpponent.name}</div>
            <div>{homeTeam.name} vs {awayTeam.name}</div> */}
            <button onClick={handleSaveData} className="btn-next">Next</button>
            </>
        ) : 
        (
            <>
            {/*<ChoosePlayersForFriendlyMatch chooseNewOpponent={chooseNewOpponent} chosenOpponent={chosenOpponent}/>*/}
            <PlayMatch chooseNewOpponent={chooseNewOpponent} chosenOpponent={chosenOpponent} homeTeam={homeTeam} awayTeam={awayTeam}/>
            </>
        )}
        
        </>

        
        
    );
}

export default ChooseOpponent;