import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import {
  Container,
  ImageBox,
  InfoBox,
  Title,
  InfoItem,
  Description,
  ActionButtons,
  Message,
  Button,
} from "./ReservationDetail.syles";

const ReservationDetail = ({ reservationNo }) => {
  const { id } = useParams();
  const [reservation, setReservation] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const { auth } = useContext(AuthContext);
  const navi = useNavigate();
  const [refreshMembers, setRefreshMembers] = useState(false);

  const handleBack = () => {
    navi(-1);
  };

  useEffect(() => {
    axios
      .get(`http://localhost/reservations/${id}`)
      .then((response) => {
        console.log(response);
        setReservation(response.data);
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
        .delete(`http://localhost/reservations/${id}`, {
          headers: {
            Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then(() => {
          setReservation({
            reservationName: "삭제중입니다...",
            reservationPlace: "삭제중입니다...",
            reservationContent: "삭제중입니다...",
          });
          setTimeout(() => {
            navi("/reservations");
          }, 3000);
        });
    }
  };

  const triggerRefreshMembers = () => {
    setRefreshMembers((prev) => !prev);
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
      <h2>우리의 </h2>
      <ImageBox>대표 이미지</ImageBox>

      <InfoBox>
        <Title>모임명</Title>
        <InfoItem>
          📅 {reservation.startDate} ~ {reservation.endDate}
        </InfoItem>
        <InfoItem>📍 {reservation.location}</InfoItem>
        <Description>{reservation.description}</Description>
      </InfoBox>

      {/* 멤버 리스트 */}
      <ActionButtons>
        <Button>참여하기</Button>
        <Button>취소하기</Button>
      </ActionButtons>
    </Container>
  );
};

export default ReservationDetail;
