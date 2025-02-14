import React from "react";
import "./BackendTheory.css";

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

const BackendTheory = () => {
  const backendData = data.filter((item) =>
    ["Java", "JDBC", "ORACLE"].includes(item.SUBJECT)
  );

  return (
    <div>
      <h1>백앤드 이론</h1>
      <ul>
        {backendData.map((item) => (
          <li key={item.SUBJECT_NO}>
            <strong>{item.SUBJECT}</strong>: <span>{item.CONTENT}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BackendTheory;
