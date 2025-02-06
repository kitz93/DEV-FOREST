package com.dev.forest.reply.model.dto;

import jakarta.validation.constraints.NotBlank;
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
	private String replyWriter;
	@NotBlank(message = "빈댓글은 허용되지 않습니다.")
	private String replyContent;
	private String createDate;

}
