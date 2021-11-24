import React, {useState, useEffect} from 'react';
import TeamService from '../services/TeamService';
import ChoosePlayersForFriendlyMatch from './ChoosePlayersForFriendlyMatch';
import Carousel from 'react-bootstrap/Carousel'
import PlayMatch from './PlayMatch';
import {Col, Row, Container} from 'react-bootstrap';
import "../css/chooseOpponent.scss";

function ChooseOpponent() {
    
    const [index, setIndex] = useState(0);

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
            setUserTeam(foundTeam);

            const homeTeamState = {
                id: foundTeam.id,
                name: foundTeam.name
            }
            setHomeTeam(homeTeamState);
        } 
    }, [])

    async function retrieveOpponents() {
        const response = await TeamService.getOfficialTeams();
        setOpponents(response.data);
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
                <Row>
                    <Col xs={12} md={5} className="homeTeam">
                        <h3 className="team-role">Home team</h3>
                        <div className="homeTeamName team">
                            {homeTeam.id === userTeam.id ?
                                (<div className="userTeam">
                                    <div className="team-name">{userTeam.name}</div>
                                    <img src="data:image/png;base64,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"/>
                            
                                </div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, true)}>
                                            <div className="teamToChoose">  
                                            <div className="team-name">{opponent.name}</div>
                                            <img src="data:image/png;base64,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"/>
                                            </div>
                                        </Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                            }
                            <div className="team-stars-rating">
                            {[...Array(5)].map((i) =>
                                <i class="fas fa-star"></i>
                            )}
                            </div>
                            <div className="team-rating">OVR: 80</div>
                            
                            
                        </div>
                        {homeTeam.id === userTeam.id ?
                        (<h3 className="team-selected">Your team</h3>)
                        :
                        (
                        chosenOpponent.id === null ?
                        (<h3 className="team-selected">Not ready</h3>)
                        :
                        (<h3 className="team-selected">Ready</h3>)
                        )
                        }
                    </Col>
                    <Col className="changeSides" xs={12} md={1}>
                    <div className="title-vs">VS</div>
                    <button onClick={changeHomeAwayTeam}><i class="fas fa-exchange-alt"></i></button>
                    </Col>
                    <Col xs={12} md={5} className="awayTeam">
                        <h3 className="team-role">Away team</h3>
                        <div className="awayTeamName team">
                        {awayTeam.id === userTeam.id ?
                                (<div className="userTeam">
                                    <div className="team-name">{userTeam.name}</div>
                                    <img src="data:image/png;base64,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"/>
                            
                                </div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, false)}>
                                            <div className="teamToChoose">  
                                            <div className="team-name">{opponent.name}</div>
                                            <img src="data:image/png;base64,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"/>
                                            </div>
                                        </Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                        }
                        <div className="team-stars-rating">
                        {[...Array(5)].map((i) =>
                        <i class="fas fa-star"></i>
                        )}
                        </div>
                        
                        <div className="team-rating">OVR: 80</div>
                        
                        </div>
                        {awayTeam.id === userTeam.id ?
                        (<h3 className="team-selected">Your team</h3>)
                        :
                        (
                        chosenOpponent.id === null ?
                        (<h3 className="team-selected">Not ready</h3>)
                        :
                        (<h3 className="team-selected">Ready</h3>)
                        )
                        }
                    </Col>
                    
                </Row>
                </Container>
            </div>
            
            <div>Chosen opponent: {chosenOpponent.name}</div>
            <div>{homeTeam.name} vs {awayTeam.name}</div>
            <button onClick={handleSaveData}>Next <i className="fas fa-angle-double-right"></i></button>
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