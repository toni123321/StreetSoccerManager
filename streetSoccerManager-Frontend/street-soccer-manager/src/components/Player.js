import React from "react";
import { useDrag } from "react-dnd";

function Player() {

  const [{ isDragging }, dragRef] = useDrag(() => ({
    type: "player",
    collect: (monitor) => ({
      isDragging: !!monitor.isDragging(),
    }),
  }));

  return (
    <div ref={dragRef} style={{ border: isDragging ? "5px solid pink" : "0px" }} className="player">
        Player
    </div>
  );
}

export default Player;