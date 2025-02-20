import {
  Button,
  ButtonForm,
  Container,
  FileInput,
  Input,
  InputForm,
  Label,
  TextArea,
  PlaceDiv,
  CancleButton,
  ReservationButton,
} from "./InsertReservation.syles";
import { useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import KakaoMap from "../Component/Map/KakaoMap";

const InsertReservation = () => {
  const { auth } = useContext(AuthContext);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [nickname, setNickname] = useState("");
  const [count, setCount] = useState(0);
  const [file, setFile] = useState(null);
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [location, setLocation] = useState("");
  const [address, setAddress] = useState("");
  const [accessToken, setAccessToken] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 상태 추가

  const navi = useNavigate();

  useEffect(() => {
    if (!auth.isAuthenticated) {
      alert("로그인이 필요합니다.");
      navi("/login");
    } else {
      setNickname(auth.nickname);
      setAccessToken(auth.accessToken);
    }
  }, []);

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    const allowedType = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
    const maxSize = 10 * 1024 * 1024;

    if (selectedFile && !allowedType.includes(selectedFile.type)) {
      alert("허용되지 않은 파일 형식입니다.");
      return;
    }

    if (selectedFile && selectedFile.size > maxSize) {
      alert("파일의 크기가 너무 큽니다. 10MB 이하로 선택해주세요.");
      return;
    }

    setFile(selectedFile);
  };

  const handleInsertReservation = (e) => {
    e.preventDefault();

    if (
      !title ||
      !content ||
      !count ||
      !startTime ||
      !endTime ||
      !location ||
      !address ||
      !file
    ) {
      alert("빈칸없이 입력해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("reservationName", title);
    formData.append("reservationContent", content);
    formData.append("reservationUser", nickname);
    formData.append("reservationCount", count);
    formData.append("reservationPlace", location);
    formData.append("placeAddress", address);
    formData.append("startTime", startTime);
    formData.append("endTime", endTime);
    formData.append("file", file);

    axios
      .post("http://localhost/reservations", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        if (response.status === 201) {
          alert("게시글이 성공적으로 작성되었습니다.");
          navi("/reservations");
        }
      })
      .catch((error) => alert(error.response.data));
  };

  return (
    <Container>
      <InputForm onSubmit={handleInsertReservation}>
        <Label>모임명</Label>
        <Input
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          type="text"
          required
          placeholder="모임명을 입력하세요."
        />

        <Label>모임설명</Label>
        <TextArea
          id="content"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          required
          placeholder="모임에 대한 설명을 입력해주세요."
        />

        <Label>모임 장소</Label>
        <KakaoMap
          isOpen={isModalOpen}
          onClose={() => setIsModalOpen(false)}
          onSelectPlace={({ place_name, address_name }) => {
            setLocation(place_name);
            setAddress(address_name);
          }}
        />

        <PlaceDiv>
          <Input
            id="location"
            type="text"
            value={`${location}${address ? ` (${address})` : ""}`}
            readOnly
            placeholder="장소를 선택하세요."
          />
          <Button type="button" onClick={() => setIsModalOpen(true)}>
            장소 선택
          </Button>
        </PlaceDiv>

        <Label>대표 예약자</Label>
        <Input id="nickname" type="text" readOnly value={nickname} />

        <Label>인원 수</Label>
        <Input
          id="count"
          type="number"
          min="1"
          value={count}
          onChange={(e) => setCount(Number(e.target.value))}
        />

        <Label>시작 시간</Label>
        <Input
          id="startTime"
          type="datetime-local"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
        />

        <Label>종료 시간</Label>
        <Input
          id="endTime"
          type="datetime-local"
          value={endTime}
          onChange={(e) => setEndTime(e.target.value)}
        />

        <Label htmlFor="file">모임 대표 이미지</Label>
        <FileInput
          id="file"
          type="file"
          accept="image/*"
          onChange={handleFileChange}
        />

        <ButtonForm>
          <CancleButton type="button" onClick={() => navi("/reservations")}>
            취소하기
          </CancleButton>
          <ReservationButton type="submit">제출하기</ReservationButton>
        </ButtonForm>
      </InputForm>
    </Container>
  );
};

export default InsertReservation;
