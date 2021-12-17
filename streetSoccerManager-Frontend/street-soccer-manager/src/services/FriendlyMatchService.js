import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/friendlyMatches");
};

const startMatch = (data, token) => {
  return http.post("/friendlyMatches/startMatch", data, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
}

const playMatch = (data, token) => {
  return http.put("/friendlyMatches/playMatch", data, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};



export default {
  getAll,
  startMatch,
  playMatch
};