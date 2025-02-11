import styled from "styled-components";
import logo from "../image/logo.jpg";

const LogoDiv = styled.div`
  width: 600px;
  height: 300px;
  margin: auto;
`;

const LogoImg = styled.img`
  width: 600px;
  height: 300px;
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
  width: 20%;
  height: 100%;
  background-color: rgba(209, 209, 209, 0.2);
  text-align: center;
  font-size: 28px;
  font-weight: 800;
  padding-top: 10px;
  float: left;
`;

const Header = () => {
  return (
    <>
      <LogoDiv>
        <LogoImg src={logo} alt="로고" />
      </LogoDiv>
      <NavDiv>
        <NavUl>
          <NavLi>스터디 룸</NavLi>
          <NavLi>퀴즈 퀴즈</NavLi>
          <NavLi>이론 공부</NavLi>
          <NavLi>커뮤니티</NavLi>
          <NavLi>마이 페이지</NavLi>
        </NavUl>
      </NavDiv>
    </>
  );
};

export default Header;
