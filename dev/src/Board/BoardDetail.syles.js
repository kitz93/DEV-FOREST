import styled from "styled-components";

export const Container = styled.div`
  max-width: 800px;
  margin: 50px auto;
  padding: 30px;
  background-color: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-radius: 12px;

  @media (max-width: 768px) {
    margin: 20px;
    padding: 20px;
  }
`;

export const Title = styled.h2`
  font-size: 1.8em;
  color: #333;
  margin-bottom: 10px;
  text-align: left;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
`;

export const Author = styled.p`
  font-size: 1em;
  color: #777;
  margin-bottom: 20px;
`;

export const ContentWrapper = styled.div`
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  min-height: 250px;
  margin-bottom: 20px;
  font-size: 1.2em;
  color: #555;
  line-height: 1.6;
`;

export const ImageContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
`;

export const Image = styled.img`
  width: 100%;
  max-width: 300px;
  border-radius: 8px;
`;

export const ButtonGroup = styled.div`
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-top: 20px;
`;

export const BackButton = styled.button`
  padding: 10px 20px;
  background-color: #3498db;
  color: #ffffff;
  border: none;
  border-radius: 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #2980b9;
  }
`;

export const EditButton = styled.button`
  padding: 10px 20px;
  background-color: #fff099;
  color: #333;
  border: none;
  border-radius: 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #fff066;
  }
`;

export const DeleteButton = styled.button`
  padding: 10px 20px;
  background-color: #e74c3c;
  color: #ffffff;
  border: none;
  border-radius: 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #c0392b;
  }
`;
