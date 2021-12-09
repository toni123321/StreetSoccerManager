import http from "../api/api-client.js";

const getTeamRating = (id, token) => {
  return http.get(`/ratings/team/${id}`, 
  {
    headers:{
    'Authorization': `Bearer ${token}`
  },
  });
};

const getPlayerRating = (id, token) => {
    return http.get(`/ratings/player/${id}`, 
    {
      headers:{
      'Authorization': `Bearer ${token}`
    },
    });
  };

export default {
  getTeamRating,
  getPlayerRating
};