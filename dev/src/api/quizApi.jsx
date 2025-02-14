// src/api/quizApi.jsx
import axios from "axios";

const API_URL = "http://localhost/quizs/random";

export const getAllQuizzes = async () => {
  return await axios.get(API_URL);
};
