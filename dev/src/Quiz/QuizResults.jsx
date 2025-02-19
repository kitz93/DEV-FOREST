import React, { useContext, useEffect } from "react";
import "./QuizResult.css";
import axios from "axios";
import { AuthContext } from "../Component/Context/AuthContext";

const QuizResults = ({ quizzes, userAnswers }) => {
  const { auth } = useContext(AuthContext);

  useEffect(() => {
    let correctCount = 0;
    let wrongCount = 0;
    for (var i = 0; i < quizzes.length; i++) {
      const result = quizzes[i].answer === userAnswers[i];
      // console.log(result);
      if (result) {
        correctCount += 1;
      } else {
        wrongCount += 1;
      }
    }
    console.log(correctCount, wrongCount);
    console.log(auth);
    axios
      .post(
        "http://localhost/rankings/insert",
        {
          correct: correctCount,
          wrong: wrongCount,
        },
        {
          headers: {
            Authorization: `Bearer ${auth.accessToken}`,
          },
        }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="quiz-results">
      <h2>퀴즈 결과</h2>
      {quizzes.map((quiz, index) => {
        // console.log(quiz);
        return (
          <div key={index}>
            <p>
              퀴즈번호: {quiz.quizNo}, 정답: {quiz.answer}, 입력한 답:{" "}
              {userAnswers[index]}
            </p>
          </div>
        );
      })}
    </div>
  );
};

export default QuizResults;
