import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import { useParams } from "react-router-dom";
import axios from "axios";
import StudyingList from "../Studying/StudyingList";
import {
  Container,
  ImageBox,
  InfoBox,
  Title,
  InfoItem,
  Description,
  Message,
  CancleButton,
} from "./ReservationDetail.syles";
import StudyingForm from "../Studying/StudyingForm";
import { useNavigate } from "react-router-dom";

const ReservationDetail = () => {
  const { id: reservationNo } = useParams();
  const [reservation, setReservation] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [refresh, setRefresh] = useState(false);
  const { auth } = useContext(AuthContext);

  const navi = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost/reservations/${reservationNo}`)
      .then((response) => {
        //console.log(response.data);
        setReservation(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("모임 정보 불러오기 실패:", error);
        setError(true);
        setLoading(false);
      });
  }, []);

  const handleRefresh = () => {
    setRefresh((prev) => !prev);
  };

  const handleCancle = () => {
    if (window.confirm("정말 삭제할거니?")) {
      axios
        .delete(`http://localhost/reservations/${reservationNo}`, {
          headers: {
            Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then(() => {
          setReservation({
            reservationName: "삭제중입니다....",
            reservationContent: "삭제중입니다...",
          });
          setTimeout(() => {
            alert("삭제가 완료되었습니다.");
            navi("/reservations");
          }, 3000);
        });
    }
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
      <h2>모임 상세</h2>
      {reservation.fileUrl && (
        <ImageBox src={reservation.fileUrl} alt="첨부 이미지" />
      )}

      <InfoBox>
        <Title>{reservation.reservationName}</Title>
        <InfoItem>
          📅 {reservation.startTime} ~ {reservation.endTime}
        </InfoItem>
        <InfoItem>📍 {reservation.reservationPlace}</InfoItem>
        <InfoItem>{reservation.placeAddress}</InfoItem>
        <Description>{reservation.reservationContent}</Description>
      </InfoBox>

      <StudyingList reservationNo={reservationNo} refresh={refresh} />

      <StudyingForm reservationNo={reservationNo} onRefresh={handleRefresh} />
      {auth.nickname === reservation.reservationUser && (
        <CancleButton onClick={handleCancle}>취소하기</CancleButton>
      )}
    </Container>
  );
};

export default ReservationDetail;
