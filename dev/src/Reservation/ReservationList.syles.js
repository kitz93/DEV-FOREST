import styled from "styled-components";

// 전체 컨테이너
export const Container = styled.div`
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Message = styled.p`
  text-align: center;
  color: ${(props) => (props.$error ? "#e74c3c" : "#2ecc71")};
  margin-bottom: 20px;
`;

// 리스트 컨테이너
export const ListContainer = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  justify-content: center;
`;

export const BodyList = styled.ul`
  list-style: none;
  padding: 0;
`;

// 리스트 아이템
export const ListItem = styled.div`
  width: 100%;
  max-width: 800px;
  display: flex;
  align-items: center;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background: white;
  transition: 0.3s;
  box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 15px;

  &:hover {
    transform: translateY(-3px);
    box-shadow: 4px 4px 12px rgba(0, 0, 0, 0.15);
  }
`;

// 썸네일 이미지
export const Thumbnail = styled.img`
  width: 150px; // 이미지 크기 키움
  height: 150px;
  border-radius: 5px;
  object-fit: cover;
`;

// 텍스트 정보 컨테이너
export const Info = styled.div`
  flex: 1;
  padding-left: 50px; // 썸네일과 텍스트 간격을 더 벌림
  display: flex;
  flex-direction: column;
`;

// 모임 제목
export const Title = styled.h3`
  margin: 0;
  font-size: 1.2rem;
  color: #333;
  font-weight: bold;
`;

// 모임 설명
export const Description = styled.p`
  margin: 8px 0; // 설명과 다른 항목 간격 추가
  font-size: 0.9rem;
  color: #666;
`;

// 모임 주소
export const Address = styled.p`
  margin: 8px 0;
  font-size: 0.9rem;
  color: #666;
`;

// 참가 인원
export const MemberCount = styled.p`
  margin: 8px 0;
  font-size: 0.9rem;
  color: #666;
`;

// 모임 생성 버튼
export const CreateButton = styled.button`
  padding: 10px 20px;
  width: 120px;
  font-size: 16px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: 0.3s;
  margin-left: auto;

  &:hover {
    background-color: #218838;
  }
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
