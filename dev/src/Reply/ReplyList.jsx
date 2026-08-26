import {
  ReplyContainer,
  ReplyItem,
  ReplyAuthor,
  ReplyContent,
  ReplyDate,
} from "./ReplyList.styles";
import { useState, useEffect, useContext } from "react";
import http from "../api/http";

const ReplyList = ({ boardNo, refresh }) => {
  const [replys, setReplys] = useState([]);

  useEffect(() => {
    http
      .get(`/replys/${boardNo}`)
      .then((response) => {
        setReplys([...response.data]);
      })
      .catch((error) => console.error("댓글 목록 불러오기 실패:", error));
  }, [refresh]);

  return (
    <ReplyContainer>
      {replys.length === 0 ? (
        <p>작성된 댓글이 없습니다.</p>
      ) : (
        replys.map((reply) => (
          <ReplyItem key={reply.replyNo}>
            <ReplyAuthor>{reply.replyWriter}</ReplyAuthor>
            <ReplyContent>{reply.replyContent}</ReplyContent>
            <ReplyDate>{new Date(reply.createDate).toLocaleString()}</ReplyDate>
          </ReplyItem>
        ))
      )}
    </ReplyContainer>
  );
};

export default ReplyList;
