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
} from "./ReservationDetail.syles";
import StudyingForm from "../Studying/StudyingForm";

const ReservationDetail = () => {
  const { id: reservationNo } = useParams();
  const [reservation, setReservation] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [refresh, setRefresh] = useState(false); // 🔹 참가자 목록 갱신을 위한 상태 추가
  const { auth } = useContext(AuthContext);

  useEffect(() => {
    axios
      .get(`http://localhost/reservations/${reservationNo}`)
      .then((response) => {
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
    setRefresh((prev) => !prev); // 🔹 참가/취소 시 상태 변경 → `StudyingList` 갱신
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

      {/* 멤버 리스트 포함 - refresh 상태 전달 */}
      <StudyingList reservationNo={reservationNo} refresh={refresh} />

      {/* 참가/취소 버튼 - onRefresh 함수 전달 */}
      <StudyingForm reservationNo={reservationNo} onRefresh={handleRefresh} />
    </Container>
  );
};

export default ReservationDetail;
