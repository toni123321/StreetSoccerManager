import React from 'react';

function ChoosePlayersForFriendlyMatch({chooseNewOpponent, chosenOpponent}) {

    
    const goBack = () => {
        chooseNewOpponent();
    }
    return (
        <>
        <div>Choose starting players for this match</div>
        <div>Opponent: {chosenOpponent.name}</div>

        <button onClick={goBack}>Go back</button>
        </>
    );
}

export default ChoosePlayersForFriendlyMatch;