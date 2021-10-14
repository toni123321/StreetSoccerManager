import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/players");
};

const getAllInTeam = (teamId) => {
  return http.get(`/players?teamId=${teamId}`);
}

const getAllInTeamAvailableForSwapping = (teamId, playerToSwapId) => {
  return http.get(`/players/availableForSwapping?teamId=${teamId}&playerToSwapId=${playerToSwapId}`);
}

const getStartingPlayers = (teamId) => {
  return http.get(`/players?teamId=${teamId}&starting=${true}`);
}

const getReserves = (teamId) => {
  return http.get(`/players?teamId=${teamId}&starting=${false}`);
}


const get = id => {
  return http.get(`/players/${id}`);
};

const create = data => {
  return http.post("/players", data);
};

const update = (data) => {
  return http.put(`/players`, data);
};

const remove = id => {
  return http.delete(`/players/${id}`);
};

const removeAll = () => {
  return http.delete(`/players`);
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