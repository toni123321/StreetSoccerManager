import React from 'react';
import { Route, Switch } from 'react-router-dom';

import About from "./About";
import Home from "./Home";
import CreateTeam from "./CreateTeam";
import LoginForm from './LoginForm';
import SignUpForm from './SignUpForm';
import GameNavbar from './GameNavbar';
import Login from './Login';
import SignUp from './SignUp';
import TeamSquad from './TeamSquad';
import ChooseOpponent from './ChooseOpponent';
import TeamSquadTest from './TeamSquadTest';
import RotatePlayersContainer from './RotatePlayersContainer';

const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/game" component={CreateTeam} />
            
            <Route exact path="/about" component={About} />

            <Route exact path="/login" component={Login} />
            <Route exact path="/signUp" component={SignUp} />

            <Route exact path="/teamSquad" component={TeamSquad} />
            
            <Route exact path="/chooseOpponent" component={ChooseOpponent} />

            <Route exact path="/teamSquadTest" component={TeamSquadTest} />
            <Route exact path="/rotatePlayers" component={RotatePlayersContainer} />

            
            {/* <Route path="/createTeam" component={CreateTeam} /> */}
        </Switch>
    );
}

export default Routers;