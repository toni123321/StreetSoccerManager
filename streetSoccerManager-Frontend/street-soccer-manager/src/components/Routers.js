import React from 'react';
import { Route, Switch } from 'react-router-dom';

import About from "./BasicPages/About";
import Home from "./BasicPages/Home";
import CreateTeam from "./Game/Team/CreateTeam";
import Login from './SignIn/Login';
import SignUp from './SignUp/SignUp';
import TeamSquad from './Game/Squad/TeamSquad';
import ChooseOpponent from './Game/PlayMatch/ChooseOpponent';
import RotatePlayersContainer from './Game/PlayerRotation/RotatePlayersContainer';
import PrivateRoute from '../routes/PrivateRoute';
import LoginRoute from '../routes/LoginRoute';
import LoginPage from './SignIn/LoginPage';
import Account from './Account';
import Contact from './BasicPages/Contact';
import NotFound from './NotFound/NotFound';
import AdminRoute from '../routes/AdminRoute';
import AddNews from './AdminView/AddNews';


const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={Home} />          
        
            <Route exact path="/login" component={Login} />
            <Route exact path="/signUp" component={SignUp} />

            <Route exact path="/teamSquad" component={TeamSquad} />
            
            <Route exact path="/chooseOpponent" component={ChooseOpponent} />

            <Route exact path="/rotatePlayers" component={RotatePlayersContainer} />


            <Route exact path="/contact" component={Contact} />

            
            
            {/* <Route path="/loginPage">
              <LoginPage />
            </Route> */}

            <LoginRoute path="/loginPage">
                <LoginPage/>
            </LoginRoute>

            <PrivateRoute path="/account">
              <Account/>
            </PrivateRoute>

            <PrivateRoute path="/about">
              <About />
            </PrivateRoute>

            <PrivateRoute path="/game">
              <CreateTeam />
            </PrivateRoute>

            <AdminRoute path="/addNews">
              <AddNews/>
            </AdminRoute>

            <Route component={NotFound} />

        </Switch>
    );


    
}

export default Routers;