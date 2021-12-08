import React, { useContext, createContext, useState, useEffect } from "react";
import Cookies from 'universal-cookie';
import jwt_decode from "jwt-decode";
import TeamService from "../services/TeamService";
import UserService from "../services/UserService";
import { useHistory } from 'react-router-dom';


const authContext = createContext();

export function ProvideAuth({ children }) {
  const auth = useProvideAuth();
  return (
    <authContext.Provider value={auth}>
      {children}
    </authContext.Provider>
  );
}

export function useAuth() {
  return useContext(authContext);
}

export function useProvideAuth() {
  let history = useHistory();
  const cookies = new Cookies();

  const [isUserLogged, setIsUserLogged] = useState(false);

  useEffect(() => {
    if(cookies.get('login-token') !== undefined){
        setIsUserLogged(true);
    }
    
  }, [])

  function signin (details, from) {
    UserService.login(details)
    .then(response => {
        console.log(response.data.Authorization);
        const token = response.data.Authorization;
        const decode_token = jwt_decode(token);

        cookies.set('login-token', token, {sameSite: 'lax'});
        console.log(cookies.get('login-token'));
        setIsUserLogged(true);
        console.log(decode_token.sub);
        getUserByEmail(decode_token.sub, token);
        history.replace(from);  
        return true;
    })
    .catch (error => {
        return false;
    })
  };

  function getUserByEmail(email, token) {
      UserService.getUserByEmail(email, token)
      .then((response) => {
          getUserTeam(response.data.id, token);
      }
      );
      // const response = await UserService.getUserByEmail(email, token);
      // console.log(response.data);
  }

  function getUserTeam(userId, token){
      TeamService.getTeamByUserId(userId, token)
      .then((response) => {
          localStorage.setItem("team", JSON.stringify(response.data));
      }
      );
  }

  function signout() {
    cookies.remove('login-token', {sameSite: 'lax'});
    setIsUserLogged(false);
    localStorage.removeItem("team");
  };

  return {
    isUserLogged,
    signin,
    signout
  };
}