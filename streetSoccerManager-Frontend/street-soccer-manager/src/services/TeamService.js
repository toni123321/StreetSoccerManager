import http from "../api/api-client.js";

const getAll = (token) => {
  return http.get("/teams", 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};

const getCustomTeams = (token) => {
  return http.get(`/teams?isCustom=${true}`, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  }
  });
};

const getOfficialTeams = (token) => {
  return http.get(`/teams?isCustom=${false}`, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  }
  });
};

const get = (id, token) => {
  return http.get(`/teams/${id}`, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};

const getTeamByUserId = (userId, token) => {
  return http.get(`/teams/user/${userId}`, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};



const create = (data, token) => {
  return http.post("/teams", data, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};

export default {
  getAll,
  get,
  getTeamByUserId,
  create,
  getCustomTeams,
  getOfficialTeams
};