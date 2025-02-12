import {
  Container,
  NaviContainer,
  BodyContainer,
  SearchContainer,
  SelectContainer,
  PageArea,
  NaviList,
  NaviItem,
  BodyList,
  BodyItem,
  BodyLeft,
  BodyRight,
  BodyImg,
  BodyTitle,
  BodyAuthor,
  BodyContent,
  BodyCount,
  AddButton,
  Paging,
  PagingBtn,
  SearchButton,
  SelectOption,
  SearchInput,
} from "./BoardList.syles";
import { useState, useEffect, useContext } from "react";
//import { AuthContext } from "../context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [page, setPage] = useState(0);
  const [next, setNext] = useState(true);

  //const { auth } = useContext(AuthContext);

  const navi = useNavigate();

  useEffect(() => {
    axios
      .get("http://localhost/boards", {
        params: {
          page: page,
        },
      })
      .then((response) => {
        setBoards([response.data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [page]);

  return (
    <Container>
      <NaviContainer>
        <NaviList>
          <NaviItem>자유</NaviItem>
          <NaviItem>공지</NaviItem>
          <NaviItem>취업</NaviItem>
        </NaviList>
        <AddButton>글쓰기</AddButton>
      </NaviContainer>

      <BodyContainer>
        <BodyList>
          {boards.map((board) => (
            <BodyItem key={board.boardNo}>
              <BodyLeft onClick={() => navi(`/boards/${board.boardNo}`)}>
                <BodyAuthor>작성자: {board.boardWriter}</BodyAuthor>
                <BodyTitle>{board.boardTitle}</BodyTitle>
                <BodyContent>{board.boardContent}</BodyContent>
                <BodyCount>조회수 {board.count}</BodyCount>
              </BodyLeft>
              <BodyRight>
                <BodyImg src={board.boardFileUrl} alt="이미지없음" />
              </BodyRight>
            </BodyItem>
          ))}
        </BodyList>
      </BodyContainer>

      <PageArea>
        <SearchContainer>
          <SelectContainer>
            <SelectOption>
              <option>작성자</option>
              <option>제목</option>
              <option>내용</option>
            </SelectOption>
          </SelectContainer>
          <SearchInput type="text" placeholder="검색어 입력" />
          <SearchButton>검색</SearchButton>
        </SearchContainer>
        <Paging>
          <PagingBtn>{"<"}</PagingBtn>
          <PagingBtn>{">"}</PagingBtn>
        </Paging>
      </PageArea>
    </Container>
  );
};

export default BoardList;
