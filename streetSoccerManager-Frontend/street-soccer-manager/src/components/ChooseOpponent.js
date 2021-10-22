import React, {useState, useEffect} from 'react';
import TeamService from '../services/TeamService';
import ChoosePlayersForFriendlyMatch from './ChoosePlayersForFriendlyMatch';
import Carousel from 'react-bootstrap/Carousel'

function ChooseOpponent() {
    
    const [index, setIndex] = useState(0);

    // const [homeTeam, setHomeTeam] = useState({
    //     id: 1,
    //     name: "HomeTeam",
    
    // });
    // const [awayTeam, setAwayTeam] = useState({
    //     id: null,
    //     name: "",
    // });

    const [chosenOpponent, setChosenOpponent] = useState({
        id: null,
        name: "",
        formation: null,
        managerName: ""
    });

    const [opponents, setOpponents] = useState([]);

    useEffect(() => {
        retrieveOpponents();
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

    const handleSelectOpponent = (opponent) => {
        //console.log(getOpponent(index).name);
        setChosenOpponent(opponent);
    }

    const handleSaveData = () => {
        // save current data (localStorage or pass as props)
        console.log("Go to next step");
        if(chosenOpponent.id !== null)
        {
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


            <Carousel interval={null}>
                {opponents.map((opponent) => 
                    <Carousel.Item key={opponent.id} onClick={() => handleSelectOpponent(opponent)}>{opponent.name}</Carousel.Item>
                )}
            </Carousel>
            <div>Chosen opponent: {chosenOpponent.name}</div>
            <button onClick={handleSaveData}>Next <i className="fas fa-angle-double-right"></i></button>
            </>
        ) : 
        (
            <>
            <ChoosePlayersForFriendlyMatch chooseNewOpponent={chooseNewOpponent} chosenOpponent={chosenOpponent}/>
            </>
        )}
        
        </>

        
        
    );
}

export default ChooseOpponent;