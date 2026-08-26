import React, { useState, useContext } from "react";
import { AuthContext } from "../Context/AuthContext";
import styled from "styled-components";
import logo from "../image/logo.jpg";
import Register from "../Register/Register";
import LoginModal from "../Login/Login";
import { useNavigate } from "react-router-dom";

const LogoDiv = styled.div`
  width: 600px;
  height: 300px;
  margin: auto;
`;

const LogoImg = styled.img`
  width: 600px;
  height: 300px;

  &:hover {
    cursor: pointer;
  }
`;

const NavDiv = styled.div`
  width: 1200px;
  height: 50px;
  margin: auto;
`;

const NavUl = styled.ul`
  width: 100%;
  height: 100%;
  list-style: none;
  padding: 0;
`;

const NavLi = styled.li`
  width: 200px;
  height: 100%;
  background-color: rgba(209, 209, 209, 0.2);
  text-align: center;
  font-size: 28px;
  font-weight: 800;
  padding-top: 10px;
  float: left;
  position: relative;

  &:hover {
    cursor: pointer;
  }

  &:hover > ul {
    display: block;
  }
`;

const DropdownUl = styled.ul`
  display: none;
  position: absolute;
  list-style: none;
  top: 100%;
  left: 0;
  width: 100%;
  background-color: white;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  z-index: 1;
  padding: 0;
  margin: 0;

  & > li {
    padding: 10px 0;
    font-size: 18px;
    font-weight: normal;
    background-color: rgba(209, 209, 209, 0.2);

    &:hover {
      background-color: rgba(209, 209, 209, 0.5);
      color: red;
    }
  }
`;

const Header = () => {
  const [isLoginModalOpen, setLoginModalOpen] = useState(false);
  const [isSignupModalOpen, setSignupModalOpen] = useState(false);
  const openLoginModal = () => setLoginModalOpen(true);
  const closeLoginModal = () => setLoginModalOpen(false);
  const openSignupModal = () => setSignupModalOpen(true);
  const closeSignupModal = () => setSignupModalOpen(false);

  const navi = useNavigate();

  const goTo = (path) => {
    navi(path);
  };

  const { auth, logout, unlinkKakao } = useContext(AuthContext);

  const handlelogout = () => {
    alert("안녕히 가세요.");
    unlinkKakao();
    logout();
    navi("/");
  };

  return (
    <>
      <LogoDiv>
        <LogoImg src={logo} alt="로고" onClick={() => goTo("/")} />
      </LogoDiv>
      <NavDiv>
        <NavUl>
          <NavLi onClick={() => goTo("/reservations")}>스터디 룸</NavLi>
          <NavLi>
            퀴즈 퀴즈
            <DropdownUl>
              <li onClick={() => goTo("/quizs")}>문제 풀기</li>
              <li onClick={() => goTo("/rankings")}>랭킹</li>
            </DropdownUl>
          </NavLi>
          <NavLi>
            이론 공부
            <DropdownUl>
              <li onClick={() => goTo("/backend")}>백 엔드</li>
              <li onClick={() => goTo("/frontend")}>프론트 엔드</li>
            </DropdownUl>
          </NavLi>
          <NavLi onClick={() => goTo("/boards")}>커뮤니티</NavLi>
          {auth.isAuthenticated ? (
            <>
              <NavLi onClick={() => goTo("/myPage")}>마이 페이지</NavLi>
              <NavLi onClick={handlelogout}>로그아웃</NavLi>
            </>
          ) : (
            <>
              <NavLi onClick={openSignupModal}>회원가입</NavLi>
              <NavLi onClick={openLoginModal}>로그인</NavLi>
            </>
          )}
        </NavUl>
      </NavDiv>
      <Register isOpen={isSignupModalOpen} onRequestClose={closeSignupModal} />
      <LoginModal isOpen={isLoginModalOpen} onRequestClose={closeLoginModal} />
    </>
  );
};

export default Header;
