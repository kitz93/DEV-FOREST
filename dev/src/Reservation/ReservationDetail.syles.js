import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
`;

export const ImageBox = styled.div`
  width: 100%;
  height: 200px;
  background-color: #f3f3f3;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  font-weight: bold;
  color: #999;
`;

export const InfoBox = styled.div`
  margin-top: 20px;
`;

export const Title = styled.h2`
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
`;

export const InfoItem = styled.p`
  font-size: 16px;
  margin: 5px 0;
`;

export const Description = styled.p`
  margin-top: 15px;
  padding: 10px;
  background: #f9f9f9;
  border-radius: 5px;
`;

export const ActionButtons = styled.div`
  display: flex;
  gap: 10px;
  margin-top: 20px;
`;

export const Button = styled.button`
  flex: 1;
  padding: 10px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background: ${(props) => (props.cancel ? "#dc3545" : "#28a745")};
  color: white;
`;

export const Message = styled.p`
  text-align: center;
  color: ${(props) => (props.$error ? "#e74c3c" : "#2ecc71")};
  margin-bottom: 20px;
`;
