import http from "../api/api-client.js";

const getAll = () => {
  return http.get("/users");
};

const get = id => {
  return http.get(`/users/${id}`);
};

const register = data => {
  return http.post("/users", data);
};

const login = data => {
  return http.post("/login", data);
};


export default {
  getAll,
  get,
  register,
  login
};