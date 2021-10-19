import React from 'react';

function RotateContainer({player, handleRotatePlayers, stopRedirect}) {

    const handleClick = () => {
        handleRotatePlayers(player);
        stopRedirect();
    }
    return (
        <div>
            <span>{player.firstName}</span>
            <button onClick={handleClick}>Change index</button>
        </div>
    );
}

export default RotateContainer;