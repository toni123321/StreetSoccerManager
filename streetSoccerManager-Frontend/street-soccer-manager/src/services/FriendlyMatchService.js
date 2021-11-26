import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/friendlyMatches");
};

const playMatch = (data, token) => {
  return http.post("/friendlyMatches", data, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};



export default {
  getAll,
  playMatch
};