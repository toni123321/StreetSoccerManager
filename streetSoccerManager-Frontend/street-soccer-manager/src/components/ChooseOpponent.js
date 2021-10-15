import React, {useState, useEffect} from 'react';
import ChoosePlayersForFriendlyMatch from './ChoosePlayersForFriendlyMatch';

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
        name: ""
    });

    const [opponents, setOpponents] = useState([
        {
            id: 1,
            name: "Barcelona"
        },
        {
            id: 2,
            name: "Juventus"
        },
        {
            id:3,
            name: "Napoli"
        },
        {
            id:4,
            name: "Arsenal"
        }
    ]);

    const [goNext, setGoNext] = useState(false);

    

    const getOpponent = (index) => {
        
        const opponentsList = opponents.map((opponent) => {
          return opponent;
        });
  
        //console.log(opponentsList.length);
        return opponents[index];
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

    const handleSelectOpponent = () => {
        console.log(getOpponent(index).name);
        setChosenOpponent(getOpponent(index));
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
    return (
        <>
        {/* {opponents.map((o) => (
            
            <div>{o.name}</div>
            
        ))} */}
        {!goNext ? 
        (
            <>
            <button onClick={handleGoBack} className="search-left-opponents"><i class="fas fa-chevron-left"></i></button>
            {/* <span>{getOpponent(index)}</span> */}
            {/* <span>{index}</span> */}
            <span onClick={handleSelectOpponent}>{getOpponent(index).name}</span>
            <button onClick={handleGoAhead} className="search-right-opponents"><i class="fas fa-chevron-right"></i></button>
            <div>Chosen opponent: {chosenOpponent.name}</div>
            <button onClick={handleSaveData}>Next <i class="fas fa-angle-double-right"></i></button>
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