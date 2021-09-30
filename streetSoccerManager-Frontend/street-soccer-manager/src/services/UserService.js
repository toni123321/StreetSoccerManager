import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/users");
};

const get = id => {
  return http.get(`/users/${id}`);
};



export default {
  getAll,
  get
};