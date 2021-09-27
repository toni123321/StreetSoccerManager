import React from 'react';
import { Route, Switch } from 'react-router-dom';

import About from "./About";
import Home from "./Home";
import CreateTeam from "./CreateTeam";
import LoginForm from './LoginForm';
import SignUpForm from './SignUpForm';
import GameNavbar from './GameNavbar';

const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/game" component={GameNavbar} />
            
            <Route exact path="/about" component={About} />

            <Route exact path="/login" component={LoginForm} />
            <Route exact path="/signUp" component={SignUpForm} />
            
            <Route path="/createTeam" component={CreateTeam} />
        </Switch>
    );
}

export default Routers;