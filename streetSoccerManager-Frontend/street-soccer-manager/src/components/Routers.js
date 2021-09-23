import React from 'react';
import { Route, Switch } from 'react-router-dom';

import About from "./About";
import Home from "./Home";
import Login from "./Login";
import CreateTeam from "./CreateTeam";

const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/about" component={About} />
            <Route exact path="/login" component={Login} />
            <Route path="/createTeam" component={CreateTeam} />
        </Switch>
    );
}

export default Routers;