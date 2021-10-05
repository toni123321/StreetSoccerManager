import React from "react";
import { useDrop } from "react-dnd";
import Player from "./Player";

function FormationPositionBox({ player, movePlayer }) {
    const [{ isOver }, dropRef] = useDrop(() => ({
      accept: "player",
      drop: () => movePlayer(),
      collect: (monitor) => ({
        isOver: monitor.isOver(),
      }),
    }));

  
    return (
      <div
        className="formationPosBox"
        ref={dropRef}
        style={{ backgroundColor: isOver ? "#bbf" : "rgba(0,0,0,.12" }}
      >
        {player ? <Player/> : "Box"}
      </div>
    );
  }

  export default FormationPositionBox;