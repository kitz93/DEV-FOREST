import React, { useEffect, useState, useRef, useContext } from "react";
import "./Quiz.css";
import QuizResults from "./QuizResults";
import axios from "axios";
import { AuthContext } from "../Component/Context/AuthContext";

const Quiz = () => {
  const [quizzes, setQuizzes] = useState([]);
  const [currentQuizIndex, setCurrentQuizIndex] = useState(0);
  const [answer, setAnswer] = useState("");
  const [userAnswers, setUserAnswers] = useState([]);
  const [timeLeft, setTimeLeft] = useState(10);
  const [showResults, setShowResults] = useState(false);
  const [quizLength, setQuizLength] = useState(0);
  const timerRef = useRef(null);
  const { auth } = useContext(AuthContext);

  useEffect(() => {
    const abc = () => {
      axios
        .get("http://localhost/quizs/random", {
          headers: {
            Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then((response) => {
          setQuizLength(response.data.length);
          setQuizzes([...response.data]);
        })
        .catch((error) => {
          console.log(error);
        });

      return;
    };

    abc();
    startTimer();

    return () => clearInterval(timerRef.current);
  }, []);

  const startTimer = () => {
    clearInterval(timerRef.current);
    setTimeLeft(10);
    //console.log(quizLength);

    timerRef.current = setInterval(() => {
      setTimeLeft((prevTime) => {
        if (prevTime >= 1) {
          // return;
          return prevTime - 1;
        } else {
          clearInterval(timerRef.current);
          // alert("시간이 초과되었습니다!");
          //setUserAnswers([...userAnswers, "시간초과"]);

          //goToNextQuestion();
          setUserAnswers((userAnswers) => [...userAnswers, "시간초과"]);
          goToNextQuestion();
        }
      });
    }, 1000);
  };

  const goToNextQuestion = () => {
    if (currentQuizIndex < 5 - 1) {
      setCurrentQuizIndex((prevIndex) => prevIndex + 1);
      //setAnswer("");
      startTimer();
    } else {
      setShowResults(true);
      clearInterval(timerRef.current);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setUserAnswers([...userAnswers, answer]);
    setAnswer("");
    goToNextQuestion();
  };

  return (
    <div className="quiz-container">
      {!showResults && (
        <div id="timer">
          타이머: <span id="time">{timeLeft}</span>
        </div>
      )}
      {!showResults ? (
        quizzes.length > 0 ? (
          <>
            <div>
              {currentQuizIndex + 1}/{quizzes.length}
            </div>
            <div>
              <h2 className="quiz-question">
                {quizzes[currentQuizIndex].question}
              </h2>
              <form className="quiz-form" onSubmit={handleSubmit}>
                {quizzes[currentQuizIndex].quizOption.length !== 0 ? (
                  <div className="quiz-options">
                    {quizzes[currentQuizIndex].quizOption.map(
                      (option, index) => (
                        <label className="quiz-option-label" key={index}>
                          <input
                            type="radio"
                            name="quizOption"
                            value={option.quizOption}
                            checked={answer === option.quizOption}
                            className="quiz-radio-input"
                            onChange={(e) => setAnswer(e.target.value)}
                          />
                          <span>{option.quizOption}</span>
                        </label>
                      )
                    )}
                  </div>
                ) : (
                  <input
                    type="text"
                    value={answer}
                    className="quiz-text-input"
                    onChange={(e) => setAnswer(e.target.value)}
                  />
                )}
                <button type="submit" className="quiz-submit-button">
                  제출
                </button>
              </form>
            </div>
          </>
        ) : (
          <p>Loading quizzes...</p>
        )
      ) : (
        <QuizResults quizzes={quizzes} userAnswers={userAnswers} />
      )}
    </div>
  );
};

export default Quiz;
