import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/formations");
};

const get = id => {
  return http.get(`/formations/${id}`);
};

const create = data => {
  return http.post("/formations", data);
};

const update = (id, data) => {
  return http.put(`/formations/${id}`, data);
};

const remove = id => {
  return http.delete(`/formations/${id}`);
};

const removeAll = () => {
  return http.delete(`/formations`);
};


export default {
  getAll,
  get,
  create,
  update,
  remove,
};