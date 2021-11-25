import http from "../api/api-client.js";

const getAll = (token) => {
  return http.get("/playersPositionInfo", token);
};

const get = (id, token) => {
  return http.get(`/playersPositionInfo/${id}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};

const create = (data, token) => {
  return http.post("/playersPositionInfo", data, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};

const update = (data, token) => {
  return http.put(`/playersPositionInfo`, data, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};

const remove = (id, token) => {
  return http.delete(`/playersPositionInfo/${id}`, 
  {headers:{
    'Authorization': `Bearer ${token}`
  }});
};



export default {
  getAll,
  get,
  create,
  update,
  remove,
};