import {
  Container,
  Title,
  Form,
  Label,
  Input,
  TextArea,
  FileInput,
  Button,
} from "./InsertBoard.styles";
import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const InsertBoard = () => {
  const { auth } = useContext(AuthContext);
  const navi = useNavigate();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [nickname, setNickname] = useState("");
  const [file, setFile] = useState(null);
  const [boardType, setBoardType] = useState("1"); // 기본값: 자유 (1)
  const [accessToken, setAccessToken] = useState("");

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

  const handleInsertBoard = (e) => {
    e.preventDefault();

    if (!title || !content) {
      alert("제목과 내용을 입력해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("boardTitle", title);
    formData.append("boardContent", content);
    formData.append("boardWriter", nickname);
    formData.append("boardType", boardType);
    if (file) {
      formData.append("file", file);
    }

    axios
      .post("http://localhost/boards", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        if (response.status === 201) {
          alert("게시글이 성공적으로 작성되었습니다.");
          navi("/boards");
        }
      })
      .catch((error) => console.log(error));
  };

  return (
    <Container>
      <Title>게시글 작성</Title>
      <Form onSubmit={handleInsertBoard}>
        <div>
          <Label htmlFor="title">제목</Label>
          <Input
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            type="text"
            required
            placeholder="제목을 입력하세요"
          />
        </div>
        <div>
          <Label htmlFor="content">내용</Label>
          <TextArea
            id="content"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
            placeholder="내용을 입력하세요"
          ></TextArea>
        </div>
        <div>
          <Label htmlFor="nickname">작성자 ID</Label>
          <Input id="nickname" type="text" readOnly value={nickname} />
        </div>
        <div>
          <Label htmlFor="boardType">게시판 종류</Label>
          <select
            id="boardType"
            value={boardType}
            onChange={(e) => setBoardType(e.target.value)}
          >
            <option value="1">자유</option>
            {auth.nickname === "ADMIN" && <option value="2">공지</option>}
            <option value="3">취업</option>
          </select>
        </div>
        <div>
          <Label htmlFor="file">파일 첨부</Label>
          <FileInput
            id="file"
            type="file"
            accept="image/*"
            onChange={handleFileChange}
          />
        </div>
        <Button type="submit">작성</Button>
      </Form>
    </Container>
  );
};

export default InsertBoard;
