import styled from "styled-components";
import {
  TestDiv,
  Body3,
  Body3Div,
  Body3Img,
  Body3P1,
  Body3P2,
  Body4Div,
  Body4Btn,
  Body5Btn,
  Body5Div,
  CardIntroDiv,
  CardIntroH3,
} from "./Main.styles";
import iconHtml from "../image/iconHtml.png";
import iconPeople from "../image/iconPeople.png";
import iconQuiz from "../image/iconQuiz.png";
import javaIcon from "../image/javaIcon.png";
import reactIcon from "../image/reactIcon.png";
import htmlcssjsIcon from "../image/HTMLCSSJSIcon.png";
import mainImg from "../image/mainImg.png";
import mainImg2 from "../image/mainImg2.png";
import mainImg3 from "../image/mainImg3.png";
import { useNavigate } from "react-router-dom";
import YouTube from "react-youtube";

const Main = () => {
  const navi = useNavigate();

  const goTo = (path) => {
    navi(path);
  };
  return (
    <>
      <br />
      <br />
      <TestDiv>
        <div
          id="carouselExampleAutoplaying"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src={mainImg} class="d-block w-100" alt="..." />
            </div>
            <div class="carousel-item">
              <img src={mainImg2} class="d-block w-100" alt="..." />
            </div>
            <div class="carousel-item">
              <img src={mainImg3} class="d-block w-100" alt="..." />
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="prev"
            style={{ background: "none", border: "none" }}
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="next"
            style={{ background: "none", border: "none" }}
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
        <br /> <br /> <br /> <br />
        <Body3>
          <Body3Div>
            <Body3Img src={iconQuiz} alt="아이콘 이미지" />
            <Body3P1>가장 손쉽게</Body3P1>
            <Body3P2>하루에 한 번 전공과 관련된 퀴즈!</Body3P2>
          </Body3Div>
          <Body3Div>
            <Body3Img src={iconHtml} alt="아이콘 이미지" />
            <Body3P1>다양한 정보를 한 눈에</Body3P1>
            <Body3P2>CSS/ HTML / JAVASCRIPT까지!</Body3P2>
          </Body3Div>
          <Body3Div>
            <Body3Img src={iconPeople} alt="아이콘 이미지" />
            <Body3P1>다양한 정보는 바로 여기!</Body3P1>
            <Body3P2>다양한 사람들과 여러 정보를!</Body3P2>
          </Body3Div>
        </Body3>
        <br />
        <CardIntroDiv>
          <CardIntroH3>주요 언어!</CardIntroH3>
        </CardIntroDiv>
        <br />
        <br />
        <br />
        <div class="card-group">
          <div class="card">
            <img src={javaIcon} class="card-img-top" alt="..." />
            <div class="card-body">
              <h5 class="card-title" style={{ textAlign: "center" }}>
                JAVA
              </h5>
            </div>
          </div>
          <div class="card">
            <img src={reactIcon} class="card-img-top" alt="..." />
            <div class="card-body">
              <h5 class="card-title" style={{ textAlign: "center" }}>
                React / VSCode
              </h5>
            </div>
          </div>
          <div class="card">
            <img src={htmlcssjsIcon} class="card-img-top" alt="..." />
            <div class="card-body">
              <h5 class="card-title" style={{ textAlign: "center" }}>
                HTML / CSS / JS
              </h5>
            </div>
          </div>
        </div>
        <br /> <br />
        <br />
        <div>
          <br />
          <CardIntroDiv>
            <CardIntroH3>기초 강의</CardIntroH3>
          </CardIntroDiv>
          <YouTube
            videoId="DNCBaeCoMug"
            opts={{
              width: "100%",
              height: "500",
              playerVars: {
                autoplay: 1,
                rel: 0,
                modestbranding: 1,
              },
            }}
            onEnd={(e) => {
              e.target.stopVideo(0);
            }}
          />
        </div>
        <br />
        <br />
        <br />
        <Body4Div>
          <p>퀴즈를 풀고싶다면?😁</p>
          <Body4Btn onClick={() => goTo("/quizs")}>퀴즈 퀴즈</Body4Btn>
        </Body4Div>
        <Body5Div>
          <p>같이 공부할 사람? ✋</p>
          <Body5Btn onClick={() => goTo("/reservations")}>스터디 모임</Body5Btn>
        </Body5Div>
        <br /> <br />
      </TestDiv>
    </>
  );
};

export default Main;
