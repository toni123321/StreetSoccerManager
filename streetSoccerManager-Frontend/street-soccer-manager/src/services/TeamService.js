import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/teams");
};

const get = id => {
  return http.get(`/teams/${id}`);
};

const create = data => {
  return http.post("/teams", data);
};

const update = (id, data) => {
  return http.put(`/teams/${id}`, data);
};

const remove = id => {
  return http.delete(`/teams/${id}`);
};

const removeAll = () => {
  return http.delete(`/teams`);
};


export default {
  getAll,
  get,
  create,
  update,
  remove,
};