import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import { AuthContext } from "../Context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

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

const MyPage = () => {
  const { auth } = useContext(AuthContext);
  const navi = useNavigate();
  const [userInfo, setUserInfo] = useState({});

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
        body: {},
      })
      .then((response) => {
        // console.log(response);
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
              {Math.floor((userInfo.correctCount / userInfo.totalCount) * 100)}%
            </Value>
          </InfoItem>
        </InfoList>
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
    </MyPageContainer>
  );
};

export default MyPage;
