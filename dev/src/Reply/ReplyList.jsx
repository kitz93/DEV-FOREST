import {
  ReplyContainer,
  ReplyItem,
  ReplyAuthor,
  ReplyContent,
  ReplyDate,
} from "./CommentList.styles";
import { useState, useEffect, useContext } from "react";
import axios from "axios";

const ReplyList = ({ boardNo, refresh }) => {
  const [replys, setReplys] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost/replys/${boardNo}`).then((response) => {
      setReplys([...response.data]);
    });
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
