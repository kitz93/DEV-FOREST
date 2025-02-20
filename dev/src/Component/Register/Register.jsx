import React, { useState } from "react";
import Modal from "react-modal";
import axios from "axios";
import styled, { createGlobalStyle } from "styled-components";
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

const RegisterButton = styled.button`
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

const Register = ({ isOpen, onRequestClose }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [nickname, setNickname] = useState("");
  const [showKakaoNicknameModal, setShowKakaoNicknameModal] = useState(false); // 카카오 닉네임 입력 모달 상태 추가
  const [kakaoId, setKakaoId] = useState("");

  const handleRegister = () => {
    axios
      .post("http://localhost/members", {
        signUp: "사이트",
        userId: email,
        userPwd: password,
        nickname: nickname,
      })
      .then((response) => {
        alert(response.data);
        setEmail("");
        setNickname("");
        setPassword("");
      })
      .catch((error) => {
        console.log(error);
        if (error.response && error.response.data) {
          const errorMsg = error.response.data;
          let alertMsg = "";
          if (errorMsg.nickname) {
            alertMsg += `${errorMsg.nickname}\n`;
          }
          if (errorMsg.userId) {
            alertMsg += `${errorMsg.userId}\n`;
          }
          if (errorMsg.userPwd) {
            alertMsg += `${errorMsg.userPwd}\n`;
          }
          if (errorMsg) {
            alertMsg += `${errorMsg}\n`;
          }
          alert(alertMsg);
        } else {
          console.log(error);
        }
      });

    onRequestClose();
  };

  const handleKakaoSuccess = (response) => {
    setKakaoId(response.profile.id);
    onRequestClose();
    setShowKakaoNicknameModal(true); // 카카오 닉네임 입력 모달 표시
  };

  const handleKakaoSubmit = () => {
    axios
      .post("http://localhost/members/sns", {
        // accessToken: kakaoAccessToken, // 저장된 accessToken 사용
        // provider: "kakao",
        signUp: "소셜",
        snsId: kakaoId,
        nickname: nickname, // 닉네임 추가
      })
      .then((res) => {
        alert("카카오 회원가입이 완료되었습니다.");
        setNickname(""); // 닉네임 초기화
        onRequestClose();
        setShowKakaoNicknameModal(false); // 카카오 닉네임 입력 모달 숨기기
      })
      .catch((error) => {
        alert(error.response.data);
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
          <Title>회원가입</Title>
          <Input
            type="email"
            placeholder="이메일"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Input
            type="text"
            placeholder="닉네임"
            value={nickname}
            onChange={(e) => setNickname(e.target.value)}
          />
          <RegisterButton onClick={handleRegister}>회원가입</RegisterButton>
          <KakaoLogin
            token="ef9fef9e05963650c0d63631821cd92c"
            onSuccess={handleKakaoSuccess}
            onFailure={handleKakaoFailure}
            render={({ onClick }) => (
              <KakaoButton onClick={onClick}>카카오로 회원가입</KakaoButton>
            )}
          />
        </ModalContent>
      </StyledModal>

      <StyledModal
        isOpen={showKakaoNicknameModal}
        onRequestClose={() => setShowKakaoNicknameModal(false)}
        overlayClassName="custom-overlay"
      >
        <ModalContent>
          <CloseButton onClick={() => setShowKakaoNicknameModal(false)}>
            &times;
          </CloseButton>
          <Title>카카오 닉네임 입력</Title>
          <Input
            type="text"
            placeholder="카카오 닉네임"
            value={nickname}
            onChange={(e) => setNickname(e.target.value)}
          />
          <RegisterButton onClick={handleKakaoSubmit}>완료</RegisterButton>
        </ModalContent>
      </StyledModal>
    </>
  );
};

export default Register;
