import { useState, useEffect, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../Component/Context/AuthContext";
import {
  BackButton,
  ButtonContainer,
  JoinButton,
  LeaveButton,
} from "./StudyingForm.styles";
import { useNavigate } from "react-router-dom";

const StudyingForm = ({ reservationNo, onRefresh }) => {
  const { auth } = useContext(AuthContext);
  const [isJoined, setIsJoined] = useState(false);

  const navi = useNavigate();

  const handleBack = () => {
    navi(-1);
  };

  useEffect(() => {
    // 참가 여부 확인
    axios
      .get(`http://localhost/studyings/${reservationNo}`)
      .then((response) => {
        const participants = response.data || [];
        const isUserJoined = participants.some(
          (p) => p.studyingUser === auth.nickname
        );
        setIsJoined(isUserJoined);
      })
      .catch((error) => console.error("참가자 목록 불러오기 실패:", error));
  }, [reservationNo, auth.nickname]); // reservationNo 또는 사용자 변경 시 확인

  const handleJoin = () => {
    const formData = new FormData();
    formData.append("refRno", reservationNo);
    formData.append("studyingUser", auth.nickname);

    if (!auth.isAuthenticated) {
      alert("댓글은 로그인을 해야만 작성할 수 있습니다.");
      return;
    } else {
      if (window.confirm("모임에 참석하실 건가요?")) {
        axios
          .post(`http://localhost/studyings`, formData, {
            headers: {
              Authorization: `Bearer ${auth.accessToken}`,
            },
          })
          .then(() => {
            setIsJoined(true);
            onRefresh();
          })
          .catch((error) => alert("현재 모임 정원이 다 찼습니다."));
      }
    }
  };

  const handleLeave = () => {
    if (window.confirm("정말 취소할 건가요?")) {
      axios
        .delete(`http://localhost/studyings/${reservationNo}`, {
          headers: {
            Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then(() => {
          setIsJoined(false);
          onRefresh(); // 참가자 목록 갱신
        })
        .catch((error) => console.error("참여 취소 실패:", error));
    }
  };

  return (
    <ButtonContainer>
      {isJoined ? (
        <LeaveButton onClick={handleLeave}>참여 취소</LeaveButton>
      ) : (
        <JoinButton onClick={handleJoin}>참여하기</JoinButton>
      )}
      <BackButton onClick={handleBack}>뒤로가기</BackButton>
    </ButtonContainer>
  );
};

export default StudyingForm;
