import React from "react";
import "./QuizResult.css";

const QuizResults = ({ quizzes, userAnswers }) => {
  return (
    <div className="quiz-results">
      <h2>결과</h2>
      {quizzes.map((quiz, index) => {
        console.log(quiz);
        return (
          <div key={index}>
            <p>
              퀴즈번호: {quiz.quizNo}, 정답: {quiz.answer}, 입력한 답:{}
              {userAnswers[index]}
            </p>
          </div>
        );
      })}
    </div>
  );
};

export default QuizResults;
