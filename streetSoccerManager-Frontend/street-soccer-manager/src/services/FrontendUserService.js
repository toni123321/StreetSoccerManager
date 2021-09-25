import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/frontendUsers");
};

const get = id => {
  return http.get(`/frontendUsers/${id}`);
};

const create = data => {
  return http.post("/frontendUsers", data);
};

const update = (id, data) => {
  return http.put(`/frontendUsers/${id}`, data);
};

const remove = id => {
  return http.delete(`/frontendUsers/${id}`);
};

const removeAll = () => {
  return http.delete(`/frontendUsers`);
};


export default {
  getAll,
  get,
  create,
  update,
  remove,
};