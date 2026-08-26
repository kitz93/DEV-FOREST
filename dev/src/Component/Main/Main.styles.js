import styled from "styled-components";

export const TestDiv = styled.div`
  width: 1000px;
  background-color: white;
  margin: auto;
`;

export const Body3 = styled.div`
  display: flex;
  justify-content: space-between;
  text-align: center;
  gap: 20px;
  margin-top: 100px;
  margin-bottom: 100px;
`;

export const Body3Div = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
`;

export const Body3Img = styled.img`
  width: 100px;
  height: auto;
  border-radius: 10px;
  max-width: 100%;
`;

export const Body3P1 = styled.p`
  margin: 5px 0;
  margin-top: 10px;
  font-weight: bold;
  font-size: 25px;
`;

export const Body3P2 = styled.p`
  font-size: 15px;
  margin: 5px 0;
  margin-top: 10px;
  font-weight: bold;
`;

export const Body4Div = styled.div`
  margin-top: 30px;
  height: 150px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
  color: white;
  background-color: rgba(47, 46, 59, 0.86);
`;

export const Body4Btn = styled.button`
  width: auto;
  max-width: 200px;
  padding: 10px 20px;
  border: none;
  background-color: #ff6f61;
  color: #fff;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s ease;
  font-size: 14px;

  &:hover {
    background-color: #ff4d4d;
  }
`;

export const Body5Div = styled.div`
  margin-top: 20px;
  height: 150px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
  color: white;
  background-color: rgba(78, 84, 121, 0.86);
`;

export const Body5Btn = styled.button`
  width: auto;
  max-width: 200px;
  padding: 10px 20px;
  border: none;
  background-color: #ff6f61;
  color: #fff;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s ease;
  font-size: 14px;

  &:hover {
    background-color: #ff4d4d;
  }
`;

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  overflow: hidden;
  background-color: #222;
  padding: 20px;
`;

export const SlideContainer = styled.div`
  display: flex;
  transition: transform 0.4s ease-in-out;
  transform: translateX(
    ${(props) => props.index * -120}px
  ); /* 한 개당 120px 이동 */
`;

export const Slide = styled.div`
  min-width: 100px;
  height: 100px;
  margin: 0 10px;
  background-color: ${(props) => props.bgColor || "#ddd"};
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  font-weight: bold;
  border-radius: 10px;
`;

export const Button = styled.button`
  background: #fff;
  border: none;
  padding: 10px 15px;
  margin: 0 10px;
  cursor: pointer;
  font-size: 18px;
  border-radius: 5px;
  &:hover {
    background: #ddd;
  }
`;
export const CardIntroDiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin: 30px 0;
`;

export const CardIntroH3 = styled.h3`
  font-size: 2rem;
  font-weight: bold;
  text-align: center;
  color: #333;
  background: linear-gradient(90deg, #ff8a00, #e52e71);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  text-transform: uppercase;
  letter-spacing: 2px;
`;
