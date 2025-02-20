import styled from "styled-components";

export const Container = styled.div`
  width: 100%;
  max-width: 700px;
  margin: 50px auto;
  padding: 30px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;

  @media (max-width: 768px) {
    margin: 20px;
    padding: 20px;
  }
`;

export const InputForm = styled.form`
  display: flex;
  flex-direction: column;
`;

export const Title = styled.h2`
  text-align: left;
  color: #333;
  margin-bottom: 20px;
`;

export const Label = styled.label`
  margin-bottom: 5px;
  color: #444;
  font-weight: bold;
`;

export const Input = styled.input`
  padding: 14px 16px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 16px;
  width: 100%;
  box-sizing: border-box;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;

  &:focus {
    border-color: #3498db;
    box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
    outline: none;
  }
`;

export const TextArea = styled.textarea`
  padding: 14px 16px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 16px;
  width: 100%;
  box-sizing: border-box;
  min-height: 120px;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
  resize: none;

  &:focus {
    border-color: #3498db;
    box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
    outline: none;
  }
`;

export const FileInput = styled.input`
  margin-bottom: 20px;
`;

export const ButtonForm = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
`;

export const CancleButton = styled.button`
  padding: 12px 20px;
  background-color: #2ecc71;
  color: #fff;
  border: none;
  border-radius: 20px;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  transition: background-color 0.3s ease, transform 0.2s ease;

  &:hover {
    background-color: #27ae60;
    transform: translateY(-2px);
  }

  &:active {
    background-color: #1e8449;
    transform: translateY(0);
  }
`;

export const ReservationButton = styled.button`
  padding: 12px 20px;
  background-color: #2ecc71;
  color: #fff;
  border: none;
  border-radius: 20px;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  transition: background-color 0.3s ease, transform 0.2s ease;

  &:hover {
    background-color: #27ae60;
    transform: translateY(-2px);
  }

  &:active {
    background-color: #1e8449;
    transform: translateY(0);
  }
`;

export const Button = styled.button`
  padding: 12px 20px;
  background-color: #2ecc71;
  color: #fff;
  border: none;
  border-radius: 20px;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  transition: background-color 0.3s ease, transform 0.2s ease;

  &:hover {
    background-color: #27ae60;
    transform: translateY(-2px);
  }

  &:active {
    background-color: #1e8449;
    transform: translateY(0);
  }
`;

export const PlaceDiv = styled.div`
  display: "flex";
  gap: "10px";
  alignitems: "center";
`;
