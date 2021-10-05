import React, {useEffect} from 'react';
import StartingPlayersContainer from "./StartingPlayersContainer";
import styles from "../css/TeamSquad.css";
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from "react-dnd-html5-backend";

const TeamSquad = () => {
    
    const retrievePlayers = () => {
        
    }

    useEffect(() => {
        return () => {
            retrievePlayers();
        };
    }, [])


    return (
        <div>
            <DndProvider backend={HTML5Backend}>
            <StartingPlayersContainer/>
            </DndProvider>
        </div>
    );
}

export default TeamSquad;