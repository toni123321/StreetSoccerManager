import React, {useState, useEffect} from 'react';
import TeamService from '../services/TeamService';
import ChoosePlayersForFriendlyMatch from './ChoosePlayersForFriendlyMatch';
import Carousel from 'react-bootstrap/Carousel'
import PlayMatch from './PlayMatch';
import {Col, Row, Container} from 'react-bootstrap';

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
                    <Col className="homeTeam">
                        <h3>Home team</h3>
                        <div className="homeTeamName">
                            {homeTeam.id === userTeam.id ?
                                (<div>{userTeam.name}</div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, true)}>{opponent.name}</Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                            }
                            
                        </div>
                    </Col>
                    <Col className="changeSides">
                    <button onClick={changeHomeAwayTeam}><i class="fas fa-exchange-alt"></i></button>
                    </Col>
                    <Col className="awayTeam">
                        <h3>Away team</h3>
                        <div className="awayTeamName">
                        {awayTeam.id === userTeam.id ?
                                (<div>{userTeam.name}</div>) :
                                (
                                    <Carousel interval={null}>
                                    {opponents.map((opponent) => 
                                        <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent, false)}>{opponent.name}</Carousel.Item>
                                    )}
                                    </Carousel>
                                )
                        }
                        </div>
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