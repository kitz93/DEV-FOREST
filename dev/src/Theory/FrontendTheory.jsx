import React, { useState } from "react";
import styled from "styled-components";

const data = [
  {
    SUBJECT_NO: 2,
    SUBJECT: "AJAX",
    CONTENT: "JavaScript와 XML 형식을 이용한 비동기적 정보 교환 기법이다.",
  },
  {
    SUBJECT_NO: 3,
    SUBJECT: "AXIOS",
    CONTENT: "AXIOS는 Promise API를 활용하는 HTTP 비동기 통신 라이브러리",
  },
  {
    SUBJECT_NO: 4,
    SUBJECT: "CSS",
    CONTENT:
      "HTML 등의 마크업 언어로 작성된 문서가 실제로 웹사이트에 표현되는 방법을 정해주는 스타일 시트 언어이다.",
  },
  {
    SUBJECT_NO: 5,
    SUBJECT: "HTML",
    CONTENT: "HTML에서는 내부요소 태그가 반드시 닫혀야한다",
  },
  {
    SUBJECT_NO: 6,
    SUBJECT: "JavaScript",
    CONTENT: "HTML, CSS와 함께 웹을 구성하는 요소 중 하나.",
  },

  {
    SUBJECT_NO: 8,
    SUBJECT: "JSON",
    CONTENT:
      "JSON의 언어 독립적 특성은 다양한 프로그래밍 언어와 플랫폼에서 데이터를 교환하는 데 이상적인 형식",
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

const FrontendTheory = () => {
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

export default FrontendTheory;
