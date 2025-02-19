import React, { useState, useContext } from "react";
import axios from "axios";
import Modal from "react-modal";
import styled, { createGlobalStyle } from "styled-components";
import { AuthContext } from "../Context/AuthContext";
import KakaoLogin from "react-kakao-login"; // KakaoLogin 컴포넌트 가져오기

const StyledModal = styled(Modal)`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  width: 320px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  outline: none;
`;

const ModalContent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Title = styled.h2`
  margin-bottom: 15px;
  font-size: 20px;
  font-weight: bold;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  margin: 8px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 16px;
`;

const LoginButton = styled.button`
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background 0.3s;

  &:hover {
    background: #0056b3;
  }
`;

const KakaoButton = styled.button`
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  background: #fee500;
  color: #3d1d1c;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background 0.3s;

  &:hover {
    background: #ecd800;
  }
`;

const CloseButton = styled.button`
  position: absolute;
  top: 10px;
  right: 15px;
  font-size: 24px;
  background: none;
  border: none;
  cursor: pointer;
`;

const GlobalStyle = createGlobalStyle`
  .custom-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
`;

Modal.setAppElement("#root");

const LoginModal = ({ isOpen, onRequestClose }) => {
  const [userId, setUserId] = useState("");
  const [userPwd, setUserPwd] = useState("");
  const { login } = useContext(AuthContext);

  const handleChangeId = (e) => {
    setUserId(e.target.value);
  };

  const handleChangePwd = (e) => {
    setUserPwd(e.target.value);
  };

  const handleLogin = () => {
    axios
      .post("http://localhost/members/login", {
        userId: userId,
        userPwd: userPwd,
      })
      .then((response) => {
        alert(`${response.data.nickname}님 환영합니다.`);
        setUserId("");
        setUserPwd("");
        const { nickname, tokens } = response.data;
        login(nickname, tokens.accessToken, tokens.refreshToken);
        onRequestClose();
      })
      .catch((error) => {
        console.log(error);
        alert(error.response.data);
      });
  };

  const handleKakaoSuccess = (response) => {
    const snsId = response.profile.id;
    const snsAccessToken = response.response.access_token;
    const snsRefreshToken = response.response.refresh_token;
    axios
      .post("http://localhost/members/snsLogin", {
        snsId: snsId,
      })
      .then((response) => {
        alert(`${response.data.nickname}님 환영합니다.`);
        const { nickname, tokens } = response.data;
        login(
          nickname,
          tokens.accessToken,
          tokens.refreshToken,
          snsAccessToken,
          snsRefreshToken
        );
        onRequestClose();
      })
      .catch((error) => {
        alert("로그인에 실패하였습니다.");
      });
  };

  const handleKakaoFailure = (error) => {
    console.log(error);
    alert("카카오 로그인에 실패하였습니다.");
  };

  return (
    <>
      <GlobalStyle />
      <StyledModal
        isOpen={isOpen}
        onRequestClose={onRequestClose}
        overlayClassName="custom-overlay"
      >
        <ModalContent>
          <CloseButton onClick={onRequestClose}>&times;</CloseButton>
          <Title>로그인</Title>
          <Input
            type="text"
            name="username"
            placeholder="이메일"
            value={userId}
            onChange={handleChangeId}
          />
          <Input
            type="password"
            name="password"
            placeholder="비밀번호"
            value={userPwd}
            onChange={handleChangePwd}
          />
          <LoginButton onClick={handleLogin}>로그인</LoginButton>
          <KakaoLogin
            token="ef9fef9e05963650c0d63631821cd92c"
            onSuccess={handleKakaoSuccess}
            onFailure={handleKakaoFailure}
            render={({ onClick }) => (
              <KakaoButton onClick={onClick}>카카오로 로그인</KakaoButton>
            )}
          />
        </ModalContent>
      </StyledModal>
    </>
  );
};

export default LoginModal;
