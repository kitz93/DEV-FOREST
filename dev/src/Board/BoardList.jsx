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
} from "./BoardList.styles";
import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [page, setPage] = useState(1);
  const [boardType, setBoardType] = useState(1);
  const [condition, setCondition] = useState("");
  const [keyword, setKeyword] = useState("");
  const [totalPage, setTotalPage] = useState(0);

  const { auth } = useContext(AuthContext);

  const navi = useNavigate();

  const fetchBoards = () => {
    axios
      .get("http://localhost/boards", {
        params: {
          page: page,
          boardType: boardType,
        },
      })
      .then((response) => {
        console.log(response);
        setBoards(response.data.boardList || []); // 게시글이 없을 경우 빈 배열 설정
        setTotalPage(response.data.pi?.maxPage || 1); // 페이지가 없으면 기본값 설정
      })
      .catch((error) => {
        console.log(error);
        setBoards([]); // 에러 발생 시 빈 배열 설정
      });
  };

  const searchBoards = () => {
    axios
      .get("http://localhost/boards/search", {
        params: {
          page: page,
          boardType: boardType,
          condition: condition,
          keyword: keyword,
        },
      })
      .then((response) => {
        console.log(response);
        setBoards(response.data.boardList);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    setBoards([]); // 새로운 게시판 타입을 선택하면 초기화
    fetchBoards();
  }, [page, boardType]);

  const handlePrevPage = () => {
    if (page > 1) setPage(page - 1);
  };

  const handleNextPage = () => {
    if (page < totalPage) setPage(page + 1);
  };

  const handleBoardTypeChange = (type) => {
    setBoardType(type);
    setPage(1);
  };

  const handleSearch = () => {
    setPage(1); // 검색 시 1페이지부터 시작
    searchBoards();
  };

  return (
    <Container>
      <NaviContainer>
        <NaviList>
          <NaviItem onClick={() => handleBoardTypeChange(1)}>자유</NaviItem>
          <NaviItem onClick={() => handleBoardTypeChange(2)}>공지</NaviItem>
          <NaviItem onClick={() => handleBoardTypeChange(3)}>취업</NaviItem>
        </NaviList>
        {auth.isAuthenticated && (
          <AddButton onClick={() => navi("/insert")}>글쓰기</AddButton>
        )}
      </NaviContainer>

      <BodyContainer>
        {boards.length === 0 ? (
          <p style={{ textAlign: "center", padding: "20px" }}>
            게시글이 없습니다.
          </p>
        ) : (
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
                  {board.boardFileUrl && (
                    <BodyImg src={board.boardFileUrl} alt="첨부이미지" />
                  )}
                </BodyRight>
              </BodyItem>
            ))}
          </BodyList>
        )}
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
          <PagingBtn onClick={handlePrevPage} disabled={page === 1}>
            {"<"}
          </PagingBtn>
          <PagingBtn onClick={handleNextPage} disabled={page === totalPage}>
            {">"}
          </PagingBtn>
        </Paging>
      </PageArea>
    </Container>
  );
};

export default BoardList;
