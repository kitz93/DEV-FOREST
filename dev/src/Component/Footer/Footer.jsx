import styled from "styled-components";

const FooterContainer = styled.footer`
  background-color: #f1f1f1;
  padding: 20px 40px;
  text-align: center;
  color: #333;
  position: relative;
  bottom: 0;
  width: 1200px;
  margin: auto;
  box-sizing: border-box;

  @media (max-width: 768px) {
    padding: 15px 20px;
  }
`;

const FooterText = styled.p`
  margin: 0;
  font-size: 1rem;
`;

const FooterInfo = styled.p`
  text-align: left;
`;

const Footer = () => {
  return (
    <FooterContainer>
      <FooterText>
        © 2025 KH JongLo B-Class DEV-FOREST. All rights reserved.
      </FooterText>
      <br />
      <FooterInfo>
        대표자명: 오은혜 <br />
        주소: 서울특별시 중구 남대문로 120 2층 B강의장 <br />
        번호 : 02-1544-9970 (이메일로 연락 주세요)
        <br />
        이메일: bclass@kh.com
      </FooterInfo>
    </FooterContainer>
  );
};

export default Footer;
