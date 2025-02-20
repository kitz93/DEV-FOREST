import React, { useState } from "react";
import styled from "styled-components";

const data = [
  { SUBJECT_NO: 1, SUBJECT: "Java", CONTENT: "객체 지향 프로그래밍이다." },
  {
    SUBJECT_NO: 7,
    SUBJECT: "JDBC",
    CONTENT:
      "Connection 객체를 미리 생성하여 보관하고 애플리케이션이 필요할 때 꺼내서 사용할 수 있도록 관리해 주는 것이 Connection Pool이다.",
  },
  {
    SUBJECT_NO: 9,
    SUBJECT: "ORACLE",
    CONTENT: "관계형 데이터베이스 관리 시스템이다.",
  },
];

const Container = styled.div`
  display: flex;
  width: 1200px;
  height: 100vh;
  margin: 10px auto 0 auto;
`;

const Sidebar = styled.div`
  width: 200px;
  border-right: 1px solid #ccc;
  padding: 10px;
  background-color: #f7f7f7;
`;

const MainContent = styled.div`
  flex-grow: 1;
  padding: 10px;
  text-align: left;
`;

const SubjectButton = styled.button`
  width: 100%;
  padding: 10px;
  margin: 5px 0;
  background-color: #ccc;
  border: 1px solid #ccc;
  cursor: pointer;
  &:hover {
    background-color: #000;
  }
`;

const SubjectContent = styled.div`
  padding: 10px;
  border-top: 1px solid #ccc;
  background-color: #f9f9f9;
`;

const BackendTheory = () => {
  const [expandedSubject, setExpandedSubject] = useState(null);

  const handleSubjectClick = (subjectNo) => {
    setExpandedSubject(expandedSubject === subjectNo ? null : subjectNo);
  };

  return (
    <Container>
      <Sidebar>
        <h2>과목</h2>
        <ul>
          {data.map((item) => (
            <li key={item.SUBJECT_NO}>
              <SubjectButton
                onClick={() => handleSubjectClick(item.SUBJECT_NO)}
              >
                {item.SUBJECT}
              </SubjectButton>
              {expandedSubject === item.SUBJECT_NO && (
                <SubjectContent>
                  <p>변수</p>
                </SubjectContent>
              )}
            </li>
          ))}
        </ul>
      </Sidebar>
      <MainContent>
        {expandedSubject ? (
          <div>
            <h1>
              {data.find((item) => item.SUBJECT_NO === expandedSubject).SUBJECT}
            </h1>
            <p>
              {data.find((item) => item.SUBJECT_NO === expandedSubject).CONTENT}
            </p>
          </div>
        ) : (
          <p>과목을 클릭하여 내용을 확인하세요.</p>
        )}
      </MainContent>
    </Container>
  );
};

export default BackendTheory;
