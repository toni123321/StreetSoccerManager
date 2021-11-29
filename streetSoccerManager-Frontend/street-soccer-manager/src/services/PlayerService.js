import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/players");
};

const getAllInTeam = (teamId, token) => {
  return http.get(`/players?teamId=${teamId}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
}

const getAllInTeamAvailableForSwapping = (teamId, playerToSwapId, token) => {
  return http.get(`/players/availableForSwapping?teamId=${teamId}&playerToSwapId=${playerToSwapId}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
}

const getStartingPlayers = (teamId) => {
  return http.get(`/players?teamId=${teamId}&starting=${true}`);
}

const getReserves = (teamId) => {
  return http.get(`/players?teamId=${teamId}&starting=${false}`);
}


const get = (id, token) => {
  return http.get(`/players/${id}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};

const create = (data, token) => {
  return http.post("/players", data,
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};

const update = (data, token) => {
  return http.put(`/players`, data), 
  {headers:{
    'Authorization': `Bearer ${token}`
  }};
};

const remove = (id, token) => {
  return http.delete(`/players/${id}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};


export default {
  getAll,
  getAllInTeam,
  getAllInTeamAvailableForSwapping,
  getStartingPlayers,
  getReserves,
  get,
  create,
  update,
  remove,
};