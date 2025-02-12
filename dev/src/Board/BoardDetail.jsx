import {
  Container,
  Title,
  Author,
  ContentWrapper,
  ImageContainer,
  Image,
  ButtonGroup,
  EditButton,
  DeleteButton,
  BackButton,
  Message,
} from "./BoardDetail.styles";
//import ReplyList from "../Reply/ReplyList";
//import ReplyForm from "../Reply/ReplyForm";
import { useState, useEffect, useContext } from "react";
//import { AuthContext } from "../context/AuthContext";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const BoardDetail = () => {
  const { id } = useParams();
  const [board, setBoard] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  //const { auth } = useContext(AuthContext);
  const navi = useNavigate();
  const [refreshComments, setRefreshComments] = useState(false); // 댓글 다시 조회하기용 상태

  const handleBack = () => {
    navi(-1);
  };

  useEffect(() => {
    axios
      .get(`http://localhost/boards/${id}`)
      .then((response) => {
        setBoard(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.log(error);
        setError(true);
        setLoading(false);
      });
  }, []);

  const handleDelete = () => {
    if (window.confirm("정말 삭제할거니?")) {
      axios
        .delete(`http://localhost/boards/${id}`, {
          headers: {
            //Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then(() => {
          setBoard({
            boardTitle: "삭제중입니다...",
            boardContent: "삭제중입니다...",
            boardWriter: "삭제중입니다...",
          });
          setTimeout(() => {
            navi("/boards");
          }, 3000);
        });
    }
  };

  const handleEdit = () => {
    navi(`/boards/${id}/edit`);
  };

  const triggerRefreshComments = () => {
    setRefreshComments((prev) => !prev);
  };

  if (loading) {
    return (
      <Container>
        <Message>로딩 중...</Message>
      </Container>
    );
  }

  if (error) {
    return (
      <Container>
        <Message>게시글을 찾을 수 없습니다.</Message>
      </Container>
    );
  }

  return (
    <Container>
      <Title>{board.boardTitle}</Title>
      <Author> 작성자 : {board.boardWriter}</Author>
      <ContentWrapper>{board.boardContent}</ContentWrapper>

      {board.boardFileUrl && (
        <ImageContainer>
          <Image src={board.boardFileUrl} alt="첨부이미지" />
        </ImageContainer>
      )}

      {/* {auth.username === board.boardWriter && ( */}
      {/* <ButtonGroup> */}
      {/* <EditButton onClick={handleEdit}>수정하기</EditButton> */}
      {/* <DeleteButton onClick={handleDelete}>삭제하기</DeleteButton> */}
      {/* </ButtonGroup> */}
      {/* )} */}

      <BackButton onClick={handleBack}>뒤로가기</BackButton>

      <hr />

      <h3>댓글</h3>
      {/* <ReplyForm boardNo={id} onSuccess={triggerRefreshComments} /> */}
      {/* <ReplyList boardNo={id} refresh={refreshComments} /> */}
    </Container>
  );
};

export default BoardDetail;
