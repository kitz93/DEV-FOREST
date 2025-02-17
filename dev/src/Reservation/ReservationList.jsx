import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  Container,
  PageArea,
  SearchContainer,
  SearchButton,
  SelectContainer,
  SelectOption,
  SearchInput,
  Paging,
  PagingBtn,
  ListContainer,
  ListItem,
  Thumbnail,
  Info,
  Title,
  Description,
  Address,
  MemberCount,
  CreateButton,
} from "./ReservationList.syles";

const ReservationList = () => {
  const [reservations, setReservations] = useState([]);
  const [page, setPage] = useState(1);
  const [condition, setCondition] = useState("title");
  const [keyword, setKeyword] = useState("");
  const [totalPage, setTotalPage] = useState(0);
  const [isSearching, setIsSearching] = useState(false);

  const { auth } = useContext(AuthContext);
  const navi = useNavigate();

  const fetchReservations = () => {
    axios
      .get("http://localhost/reservations", {
        params: {
          page: page,
        },
      })
      .then((response) => {
        console.log(response.data);
        setReservations(response.data.reservationList || []);
        setTotalPage(response.data.pi?.maxPage || 1);
      })
      .catch(() => setReservations([]));
  };

  const searchReservations = () => {
    axios
      .get("http://localhost/reservations/search", {
        params: {
          page: page,
          condition: condition,
          keyword: keyword,
        },
      })
      .then((response) => {
        setReservations(response.data.reservationList || []);
        setTotalPage(response.data.pi?.maxPage || 1);
      })
      .catch(() => setReservations([]));
  };

  useEffect(() => {
    isSearching ? searchReservations() : fetchReservations();
  }, [page, isSearching]);

  const handlePrevPage = () => {
    if (page > 1) setPage(page - 1);
  };

  const handleNextPage = () => {
    if (page < totalPage) setPage(page + 1);
  };

  const handleSearch = (e) => {
    e.preventDefault();
    setIsSearching(true);
    searchReservations();
  };

  const handleDetailClick = (reservationNo) => {
    if (!auth.isAuthenticated) {
      alert("로그인이 필요합니다!");
      navi("/reservations");
      return;
    }
    navi(`/reservations/${reservationNo}`);
  };

  return (
    <Container>
      {auth.isAuthenticated && (
        <CreateButton onClick={() => navi("/create")}>모임 생성</CreateButton>
      )}
      <ListContainer>
        {reservations == null || reservations.length === 0 ? (
          <p style={{ textAlign: "center", padding: "20px" }}>
            등록된 모임이 없습니다.
          </p>
        ) : (
          reservations.map((res) => (
            <ListItem key={res.reservationNo}>
              <Thumbnail
                onClick={() => handleDetailClick(res.reservationNo)}
                src={res.fileUrl || "default_image.jpg"}
                alt="모임 이미지"
              />
              <Info onClick={() => handleDetailClick(res.reservationNo)}>
                <Title>{res.reservationName}</Title>
                <Description>{res.reservationContent}</Description>
                <Address>{res.placeAddress}</Address>
                <MemberCount>
                  👤 {res.currentMembers}명/ {res.reservationCount}명
                </MemberCount>
              </Info>
            </ListItem>
          ))
        )}
      </ListContainer>

      <PageArea>
        <SearchContainer>
          <SelectContainer>
            <SelectOption
              value={condition}
              onChange={(e) => setCondition(e.target.value)}
            >
              <option value="title">제목</option>
              <option value="place">장소</option>
            </SelectOption>
          </SelectContainer>
          <SearchInput
            type="text"
            placeholder="검색어 입력"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />
          <SearchButton onClick={handleSearch}>검색</SearchButton>
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

export default ReservationList;
