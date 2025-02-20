import styled from "styled-components";

export const Container = styled.div`
  padding: 20px;
`;

export const MemberBox = styled.div`
  border: 2px solid black;
  padding: 15px;
  width: 350px;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
`;

export const Host = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  padding-bottom: 5px;
  border-bottom: 2px solid #ddd;
`;

export const CrownIcon = styled.span`
  font-size: 20px;
  margin-right: 10px;
`;

export const HostName = styled.span`
  font-weight: bold;
  font-size: 16px;
  color: #333;
`;

export const MemberList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
`;

export const MemberItem = styled.li`
  padding: 8px 0;
  border-bottom: 1px dashed #aaa;
  font-size: 14px;
  color: #555;

  &:last-child {
    border-bottom: none;
  }
`;

export const MoreButton = styled.button`
  margin-top: 10px;
  padding: 8px 12px;
  border: none;
  background-color: #007bff;
  color: white;
  font-size: 14px;
  font-weight: bold;
  cursor: pointer;
  border-radius: 5px;
  display: block;
  width: 100%;
  text-align: center;

  &:hover {
    background-color: #0056b3;
  }
`;
