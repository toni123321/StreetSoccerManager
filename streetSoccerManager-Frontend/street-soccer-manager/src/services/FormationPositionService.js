import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/formationPositions");
};

const getAllByTeamAndFormation = (teamId, formationId) => {
    return http.get(`/formationPositions?teamId=${teamId}&formationId=${formationId}`);
}

const getStartingPositionsByTeamAndFormation = (teamId, formationId) => {
  return http.get(`/formationPositions?teamId=${teamId}&formationId=${formationId}&starting=${true}`);
}

const getPositionsForReservesByTeamAndFormation = (teamId, formationId) => {
  return http.get(`/formationPositions?teamId=${teamId}&formationId=${formationId}&starting=${false}`);
}

const get = id => {
  return http.get(`/formationPositions/${id}`);
};

const create = data => {
  return http.post("/formationPositions", data);
};

const update = (data) => {
  return http.put("/formationPositions", data);
};

const remove = id => {
  return http.delete(`/formationPositions/${id}`);
};

const removeAll = () => {
  return http.delete(`/formationPositions`);
};


export default {
  getAll,
  getAllByTeamAndFormation,
  getStartingPositionsByTeamAndFormation,
  getPositionsForReservesByTeamAndFormation,
  get,
  create,
  update,
  remove,
};