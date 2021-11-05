import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/friendlyMatches");
};

const playMatch = data => {
  return http.post("/friendlyMatches", data);
};



export default {
  getAll,
  playMatch
};