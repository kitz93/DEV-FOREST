import { useState, useEffect } from "react";
import axios from "axios";
import {
  Container,
  MemberBox,
  Host,
  CrownIcon,
  HostName,
  MemberList,
  MemberItem,
  MoreButton,
} from "./StudyingList.styles";

const StudyingList = ({ reservationNo, refresh }) => {
  const [studyings, setStudyings] = useState([]);
  const [showAll, setShowAll] = useState(false);

  useEffect(() => {
    axios
      .get(`http://localhost/studyings/${reservationNo}`)
      .then((response) => {
        console.log(response.data);
        setStudyings(response.data || []);
      })
      .catch((error) => console.error("참가자 목록 불러오기 실패:", error));
  }, [reservationNo, refresh]);

  // 개설자와 일반 참가자 구분 (첫 번째 멤버를 개설자로 가정)
  const host = studyings.length > 0 ? studyings[0] : null;
  const members = studyings.length > 1 ? studyings.slice(1) : [];

  return (
    <Container>
      <MemberBox>
        {host && (
          <Host>
            <CrownIcon>👑</CrownIcon>
            <HostName>{host.studyingUser}</HostName>
          </Host>
        )}
        <MemberList>
          {members.slice(0, showAll ? members.length : 3).map((member) => (
            <MemberItem key={member.studyingNo}>
              🧑‍💻 {member.studyingUser}
            </MemberItem>
          ))}
        </MemberList>
        {members.length > 3 && !showAll && (
          <MoreButton onClick={() => setShowAll(true)}>더보기</MoreButton>
        )}
      </MemberBox>
    </Container>
  );
};

export default StudyingList;
