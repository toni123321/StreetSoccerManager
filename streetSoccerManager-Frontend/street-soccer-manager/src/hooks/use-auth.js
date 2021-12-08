import React, { useContext, createContext, useState, useEffect } from "react";



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
  const [user, setUser] = useState(null);
  const [isUserLogged, setIsUserLogged] = useState(false);

  useEffect(() => {
    // if(cookies.get('login-token') !== undefined){
    //     setIsUserLogged(true);
    //     // history.push("/game");
    // }
    setIsUserLogged(true);
  }, [])

  const signin = cb => {
    setIsUserLogged(true);
    cb();
  };

  const signout = cb => {
    setIsUserLogged(false);
    cb();
  };

  return {
    isUserLogged,
    signin,
    signout
  };
}