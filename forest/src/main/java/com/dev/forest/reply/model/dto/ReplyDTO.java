package com.dev.forest.reply.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReplyDTO {
	
	private Long replyNo;
	private Long refBno;
	private String nickname;
	private String replyContent;
	private String createDate;

}
