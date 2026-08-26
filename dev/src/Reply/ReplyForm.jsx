import { FormContainer, TextArea, SubmitButton } from "./ReplyForm.styles";
import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../Component/Context/AuthContext";
import http, { getErrorMessage } from "../api/http";

const ReplyForm = ({ boardNo, onSuccess }) => {
  const [reply, setReply] = useState("");
  const { auth } = useContext(AuthContext);

  const handleInsertReply = (e) => {
    e.preventDefault();

    if (reply.trim() === "") {
      alert("댓글을 작성하고 눌러야 합니다.");
      return;
    }

    const formData = new FormData();
    formData.append("refBno", boardNo);
    formData.append("replyWriter", auth.nickname);
    formData.append("replyContent", reply);

    if (!auth.isAuthenticated) {
      alert("댓글은 로그인을 해야만 작성할 수 있습니다.");
      return;
    } else {
      http
        .post("/replys", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `Bearer ${auth.accessToken}`,
          },
        })
        .then((response) => {
          if (response.status === 201) {
            setReply("");
            onSuccess();
          }
        })
        .catch((error) => {
          alert(getErrorMessage(error, "댓글 작성에 실패했습니다."));
        });
    }
  };

  return (
    <FormContainer onSubmit={handleInsertReply}>
      <TextArea
        onChange={(e) => setReply(e.target.value)}
        value={reply}
        placeholder="댓글을 입력해주세요."
        rows="4"
      />
      <SubmitButton type="sybmit">작성하기</SubmitButton>
    </FormContainer>
  );
};

export default ReplyForm;
