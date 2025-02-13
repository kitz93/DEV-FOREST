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

const Footer = () => {
  return (
    <FooterContainer>
      <FooterText>
        © 2025 KH JongLo B-Class DEV-FOREST. All rights reserved.
      </FooterText>
    </FooterContainer>
  );
};

export default Footer;
