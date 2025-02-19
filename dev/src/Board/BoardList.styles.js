import styled from "styled-components";

export const Container = styled.div`
  max-width: 1000px;
  margin: 50px auto;
  padding: 20px;
  background-color: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-radius: 12px;

  @media (max-width: 768px) {
    margin: 20px;
    padding: 15px;
  }
`;

export const Message = styled.p`
  text-align: center;
  color: ${(props) => (props.$error ? "#e74c3c" : "#2ecc71")};
  margin-bottom: 20px;
`;

export const NaviContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

export const NaviList = styled.div`
  display: flex;
  gap: 15px;
`;

export const NaviItem = styled.button`
  background: none;
  border: none;
  padding: 10px 20px;
  font-size: 20px;
  cursor: pointer;
  border-radius: 8px;
  transition: 0.3s;

  &:hover {
    background-color: #f0f0f0;
  }
`;

export const AddButton = styled.button`
  padding: 10px 20px;
  width: 120px;
  font-size: 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    background-color: #0056b3;
  }
`;

export const BodyContainer = styled.div``;

export const BodyList = styled.ul`
  list-style: none;
  padding: 0;
`;

export const BodyItem = styled.li`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #ddd;
`;

export const BodyLeft = styled.div`
  flex: 1;
`;

export const BodyTitle = styled.h3`
  margin: 5px 0;
`;

export const BodyAuthor = styled.span`
  font-size: 14px;
  color: #666;
`;

export const BodyContent = styled.p`
  font-size: 14px;
  color: #333;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 300px;
`;

export const BodyCount = styled.span`
  font-size: 12px;
  color: #888;
`;

export const BodyRight = styled.div`
  flex-shrink: 0;
`;

export const BodyImg = styled.img`
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
`;

export const PageArea = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  gap: 10px;
`;

export const SearchContainer = styled.form`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const SelectContainer = styled.div`
  font-size: 16px;
`;

export const SelectOption = styled.select`
  padding: 10px;
`;

export const SearchInput = styled.input`
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 4px;
`;

export const SearchButton = styled.button`
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    background-color: #0056b3;
  }
`;

export const Paging = styled.ul`
  list-style: none;
  display: flex;
  gap: 15px;
  margin-right: 50px;
`;

export const PagingBtn = styled.li`
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    background-color: #f0f0f0;
  }
`;
