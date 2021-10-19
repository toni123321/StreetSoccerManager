import React, {useState, useEffect} from 'react';
import PlayerService from '../services/PlayerService';
import RotateContainer from './RotateContainer';


function TeamSquadTest() {

    const initialTeamState = {
        id: null,
        name: "",
        formation: {
            id:null,
            name: ""
        },
        manager:"",
        user: {
            "id": null,
            "email": "",
            "password": "",
            "nickname": "",
            "points": null
        }
    }
    // Current team
    const [team, setTeam] = useState(initialTeamState);


    const playerInitialState = {
        id: null,
        positionIndex: null,
        firstName: "",
        lastName: "",
        dob: null,
        price: null,
        defaultPosition: "",
        currentPosition: "",
        kitNr: null,
        playerStats: null,
        team: null,
        starting: true
    }
    const [selectedPlayer, setSelectedPlayer] = useState(playerInitialState);

    // team squad
    const [players, setPlayers] = useState([]);

    const [redirect, setRedirect] = useState(false);

    useEffect(() => {
        const createdTeam = localStorage.getItem("team");
        if (createdTeam) {
          const foundTeam = JSON.parse(createdTeam);
          
          //Set the team
          setTeam(foundTeam);
          console.log("here");
          
          // Set the players
          retrievePlayers(foundTeam.id);
        } 
    }, []);


    // Retrieve all players from the backend
    // const retrievePlayers = async(teamId) => {
    //     PlayerService.getAllInTeam(teamId)
    //       .then(response => {
            
    //         setPlayers(response.data);
    //       })
    //       .catch(e => {x
    //         console.log(e);
    //       });
    // }

    async function retrievePlayers(teamId) {
        const response = await PlayerService.getAllInTeam(teamId);
        setPlayers(response.data);
    }
  
    const updatePlayer = (player) => {
        PlayerService.update(player)
            .then(response => {
                setSelectedPlayer(response.data);
            })
            .catch(e => {
                console.log(e);
        });
    }

    const handleRotatePlayers = (player) => {
        let newIndex = player.positionIndex + 10;
        player.positionIndex = newIndex;
        console.log(player.positionIndex);
        updatePlayer(player);
        retrievePlayers(team.id);


        // const newPlayersState = players.map((p) => {
        //     if (p.id === player.id) {
        //       p.positionIndex = player.positionIndex;
        //     }
        //     return p;
        //   });
        //   setPlayers(newPlayersState);
    }

    const handleRedirect = (player) => {
        setRedirect(true);
        setSelectedPlayer(player);
    }

    const stopRedirect = () => {
        setRedirect(false);
    }
    return (
        <>
        {redirect === false ? 
        (
        <ul>
        
        {players && players.map((player) => ( 

            <li key={player.id}>
                <span>[{player.positionIndex}] {player.firstName} {player.currentPosition}</span>
                <button onClick={() => handleRotatePlayers(player)}>Change index</button>
                {/* <button onClick={() => handleRedirect(player)}>Redirect</button> */}
            </li>
        ))}
        
        </ul>
        )
        :
        (
        <RotateContainer player={selectedPlayer} handleRotatePlayers={handleRotatePlayers} stopRedirect={stopRedirect}/>
        )
        }
        </>
        
    );
}

export default TeamSquadTest;