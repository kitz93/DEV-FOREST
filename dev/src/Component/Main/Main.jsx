import styled from "styled-components";

const TestDiv = styled.div`
  width: 1000px;
  background-color: skyblue;
  margin: auto;
`;

const Main = () => {
  return (
    <>
      <TestDiv>
        <h1>
          메인임
          <br />
          <br />
          로고 클릭하면 메인화면으로 이동
          <br />
          <br />
          카카오 회원가입 및 로그인 가능
          <br />
          <br />
          기능은 아직
          <br />
          <br />
        </h1>
      </TestDiv>
    </>
  );
};

export default Main;
