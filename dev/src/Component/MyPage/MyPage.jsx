import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import { AuthContext } from "../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Modal from "react-modal";

const MyPageContainer = styled.div`
  width: 80%;
  max-width: 1000px;
  margin: 30px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-family: "Arial, sans-serif";
`;

const Title = styled.h1`
  font-size: 2.5rem;
  margin-bottom: 20px;
  color: #333;
  text-align: center;
`;

const InfoList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
  text-align: center;
`;

const InfoItem = styled.li`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #ddd;
`;

const Label = styled.span`
  font-weight: bold;
  color: #555;
  margin-right: 10px;
  text-align: right;
  flex: 1;
`;

const Value = styled.span`
  color: #777;
  text-align: left;
  flex: 1;
`;

const ModalContent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Input = styled.input`
  width: 80%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
`;

const StyledModal = styled(Modal)`
  position: absolute;
  top: 50%;
  left: 50%;
  right: auto;
  bottom: auto;
  margin-right: -50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 20px;
  width: 500px;
`;

const Button = styled.button`
  width: 80%;
  padding: 10px;
  margin: 10px 0;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

const ButtonGroup = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  gap: 10px;
`;

const SmallButton = styled(Button)`
  width: 45%;
`;

const MyPage = () => {
  const { auth, logout } = useContext(AuthContext);
  const navi = useNavigate();
  const [userInfo, setUserInfo] = useState({});
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [password, setPassword] = useState("");
  const [pwdModalIsOpen, setPwdModalIsOpen] = useState(false);
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const handleDeleteMember = () => {
    const res = window.confirm("진짜 탈퇴?");
    if (res) {
      if (userInfo.signUp === "사이트") {
        setModalIsOpen(true);
      } else {
        axios
          .delete("http://localhost/members/sns", {
            headers: { Authorization: `Bearer ${auth.accessToken}` },
          })
          .then((response) => {
            alert(response.data);
            logout();
          })
          .catch((error) => {
            console.log(error);
          });
      }
    }
  };

  const handleChangePwd = () => {
    setPwdModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  const closePwdModal = () => {
    setPwdModalIsOpen(false);
  };

  const handlePasswordSubmit = () => {
    axios
      .delete("http://localhost/members", {
        headers: { Authorization: `Bearer ${auth.accessToken}` },
        data: { userPwd: password },
      })
      .then((response) => {
        alert(response.data);
        closeModal();
        logout();
        navi("/");
      })
      .catch((error) => {
        console.log(error);
        alert(error.response.data);
      });
  };

  const handleChangePasswordSubmit = () => {
    axios
      .put(
        "http://localhost/members",
        {
          currPwd: currentPassword,
          newPwd: newPassword,
        },
        {
          headers: { Authorization: `Bearer ${auth.accessToken}` },
        }
      )
      .then((response) => {
        alert(response.data + "\n로그아웃 됩니다. 다시 로그인 해주세요.");
        closePwdModal();
        logout();
        navi("/");
      })
      .catch((error) => {
        alert(
          error.response.data.newPwd
            ? error.response.data.newPwd
            : error.response.data
        );
      });
  };

  useEffect(() => {
    if (auth.isAuthenticated === false) return;

    if (!auth.isAuthenticated) {
      alert("로그인 후 이용 가능합니다.");
      navi("/");
      return;
    }

    axios
      .get("http://localhost/members/myPage", {
        headers: { Authorization: `Bearer ${auth.accessToken}` },
      })
      .then((response) => {
        setUserInfo(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [auth, navi]);

  return (
    <MyPageContainer>
      <Title>마이 페이지</Title>
      {userInfo.signUp === "사이트" ? (
        <>
          <InfoList>
            <InfoItem>
              <Label>가입 방식 :</Label>
              <Value>{userInfo.signUp}</Value>
            </InfoItem>
            <InfoItem>
              <Label>아이디 :</Label>
              <Value>{userInfo.userId}</Value>
            </InfoItem>
            <InfoItem>
              <Label>닉네임 :</Label>
              <Value>{userInfo.nickname}</Value>
            </InfoItem>
            <InfoItem>
              <Label>총 문제 수 :</Label>
              <Value>{userInfo.totalCount}</Value>
            </InfoItem>
            <InfoItem>
              <Label>정답 문제 수 :</Label>
              <Value>{userInfo.correctCount}</Value>
            </InfoItem>
            <InfoItem>
              <Label>오답 문제 수 :</Label>
              <Value>{userInfo.wrongCount}</Value>
            </InfoItem>
            <InfoItem>
              <Label>정답률 :</Label>
              <Value>
                {Math.floor(
                  (userInfo.correctCount / userInfo.totalCount) * 100
                )}
                %
              </Value>
            </InfoItem>
          </InfoList>
        </>
      ) : (
        <InfoList>
          <InfoItem>
            <Label>가입 방식 :</Label>
            <Value>{userInfo.signUp}</Value>
          </InfoItem>
          <InfoItem>
            <Label>SNS-아이디 :</Label>
            <Value>{userInfo.snsId}</Value>
          </InfoItem>
          <InfoItem>
            <Label>닉네임 :</Label>
            <Value>{userInfo.nickname}</Value>
          </InfoItem>
          <InfoItem>
            <Label>총 문제 수 :</Label>
            <Value>{userInfo.totalCount}</Value>
          </InfoItem>
          <InfoItem>
            <Label>정답 문제 수 :</Label>
            <Value>{userInfo.correctCount}</Value>
          </InfoItem>
          <InfoItem>
            <Label>오답 문제 수 :</Label>
            <Value>{userInfo.wrongCount}</Value>
          </InfoItem>
          <InfoItem>
            <Label>정답률 :</Label>
            <Value>
              {Math.floor((userInfo.correctCount / userInfo.totalCount) * 100)}%
            </Value>
          </InfoItem>
        </InfoList>
      )}
      <ButtonGroup>
        {userInfo.signUp === "사이트" && (
          <SmallButton onClick={handleChangePwd}>비밀번호 수정</SmallButton>
        )}
        <SmallButton onClick={handleDeleteMember}>회원 탈퇴</SmallButton>
      </ButtonGroup>
      <StyledModal isOpen={modalIsOpen} onRequestClose={closeModal}>
        <ModalContent>
          <h2>비밀번호 입력</h2>
          <Input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="비밀번호를 입력하세요"
          />
          <ButtonGroup>
            <Button onClick={handlePasswordSubmit}>탈퇴</Button>
            <Button onClick={closeModal}>취소</Button>
          </ButtonGroup>
        </ModalContent>
      </StyledModal>
      <StyledModal isOpen={pwdModalIsOpen} onRequestClose={closePwdModal}>
        <ModalContent>
          <h2>비밀번호 변경</h2>
          <Input
            type="password"
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
            placeholder="현재 비밀번호를 입력하세요"
          />
          <Input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            placeholder="새로운 비밀번호를 입력하세요"
          />
          <ButtonGroup>
            <Button onClick={handleChangePasswordSubmit}>변경</Button>
            <Button onClick={closePwdModal}>취소</Button>
          </ButtonGroup>
        </ModalContent>
      </StyledModal>
    </MyPageContainer>
  );
};

export default MyPage;
