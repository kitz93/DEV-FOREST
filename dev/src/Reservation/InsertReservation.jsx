import {
  Button,
  ButtonForm,
  Container,
  FileInput,
  Input,
  InputForm,
  Label,
  TextArea,
  Title,
} from "./InsertReservation.syles";
import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Content } from "../Board/BoardDetail.styles";

const InsertReservation = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [nickname, setNickname] = useState("");
  const [count, setCount] = useState(0);
  const [file, setFile] = useState(null);
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  const navi = useNavigate();

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

  return (
    <Container>
      <InputForm>
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
          type="text"
          required
          placeholder="모임에 대한 설명을 입력해주세요."
        />

        <Label>대표 예약자</Label>
        <Input id="nickname" type="text" readOnly value={nickname} />

        <Label>인원 수</Label>
        <Input
          id="count"
          type="number"
          min="1"
          value={count}
          onChange={(e) => setCount(e.target.value)}
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
          <Button>제출하기</Button>
          <Button
            onClick={() => {
              navi("/reservations");
            }}
          >
            취소하기
          </Button>
        </ButtonForm>
      </InputForm>
    </Container>
  );
};

export default InsertReservation;
