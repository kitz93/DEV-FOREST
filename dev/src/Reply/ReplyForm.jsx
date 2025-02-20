import { FormContainner, TextArea, SubmitButton } from "./ReplyForm.styles";
import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import axios from "axios";

const ReplyForm = ({ boardNo, onSuccess }) => {
  const [reply, setReply] = useState("");
  const { auth } = useContext(AuthContext);

  const handleInsertReply = (e) => {
    e.preventDefault();

    if (reply.trim() === "") {
      alert("댓글을 작성하고 눌러야 합니다.");
      return;
    }

    if (auth.isAuthenticated) {
      alert("댓글은 로그인을 해야만 작성할 수 있습니다.");
      return;
    } else {
      axios
        .post(
          "http//localhost/replys",
          {
            refBno: boardNo,
            replyWriter: auth.username,
            replyContent: reply,
          },
          {
            headers: {
              Authorization: `Bearer ${auth.accessToken}`,
            },
          }
        )
        .then((response) => {
          if (response.status === 201) {
            setReply("");
            onSuccess();
          }
          //console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <FormContainner onSubmit={handleInsertReply}>
      <TextArea
        onChange={(e) => setReply(e.target.value)}
        value={reply}
        placeholder="댓글을 입력해주세요."
        rows="4"
      />
      <SubmitButton type="sybmit">작성하기</SubmitButton>
    </FormContainner>
  );
};

export default ReplyForm;
