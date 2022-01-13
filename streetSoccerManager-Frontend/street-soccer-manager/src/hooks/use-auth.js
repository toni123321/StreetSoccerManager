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

  // GUEST, USER, ADMIN
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    if(cookies.get('login-token') !== undefined){
        setIsUserLogged(true);
        const token = cookies.get('login-token') 
        const decode_token = jwt_decode(token);
        if(decode_token.role === "ADMIN"){
          setIsAdmin(true);
        }
    }
  }, [])

  function signin (details, from) {
    const promise = UserService.login(details)
    const dataPromise = promise.then(response => {
        const token = response.data.Authorization;
        const decode_token = jwt_decode(token);
        cookies.set('login-token', token, {sameSite: 'lax'});
        setIsUserLogged(true);
        getUserByEmail(decode_token.sub, token)
        .then((response) => response)
        .catch(err=> err)
        
        if(decode_token.role === "ADMIN"){
          setIsAdmin(true);
        }
    })
    return dataPromise;
  };

  function signup (details, from) {
    const promise = UserService.register(details)
    const dataPromise = promise.then(response => response.data.firstName)
    return dataPromise;
  };

  function getUserByEmail(email, token) {
      const dataPromise = UserService.getUserByEmail(email, token)
      .then((response) => {
          getUserTeam(response.data.id, token)
      })
      .catch((err) => err)
      return dataPromise;
  }

  function getUserTeam(userId, token){
      const dataPromise = TeamService.getTeamByUserId(userId, token)
      .then((response) => {
          localStorage.setItem("team", JSON.stringify(response.data));
      })
      .catch(err => err)
      return dataPromise;
  }

  function signout() {
    cookies.remove('login-token', {sameSite: 'lax'});
    setIsUserLogged(false);
    setIsAdmin(false);
    localStorage.removeItem("team");
  };

  return {
    isAdmin,
    isUserLogged,
    signin,
    signup,
    signout
  };
}