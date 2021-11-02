import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/playersPositionInfo");
};

const get = id => {
  return http.get(`/playersPositionInfo/${id}`);
};

const create = data => {
  return http.post("/playersPositionInfo", data);
};

const update = (data) => {
  return http.put(`/playersPositionInfo`, data);
};

const remove = id => {
  return http.delete(`/playersPositionInfo/${id}`);
};



export default {
  getAll,
  get,
  create,
  update,
  remove,
};