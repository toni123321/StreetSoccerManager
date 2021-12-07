import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';

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
import RotatePlayersContainer from './RotatePlayersContainer';
import Cookies from 'universal-cookie';

const Routers = () => {
    const cookies = new Cookies();

    return (
        <Switch>
            

            <Route exact path="/" component={Home} />
            <Route exact path="/game" component={CreateTeam} />            
            
            <Route exact path="/about" component={About} />

            <Route exact path="/login" component={Login} />
            <Route exact path="/signUp" component={SignUp} />

            <Route exact path="/teamSquad" component={TeamSquad} />
            
            <Route exact path="/chooseOpponent" component={ChooseOpponent} />

            <Route exact path="/rotatePlayers" component={RotatePlayersContainer} />

            {/* {cookies.get('login-token') !== undefined 
            ?
            <Route exact path="/game" component={CreateTeam} />
            :
            <Redirect to="/login"/>
            } */}
            
            
            {/* <Route path="/createTeam" component={CreateTeam} /> */}
        </Switch>
    );
}

export default Routers;