package com.dev.forest.board.model.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class BoardDTO {
	private Long boardNo;

	// 화면 표시용 닉네임. 작성자 판별에는 쓰지 않는다(principal 기준 writerNo 사용).
	private String boardWriter;

	private Long writerNo;

	@NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
	private String boardTitle;
	
	@NotBlank(message = "게시글 내용은 비어있을 수 없습니다.")
	private String boardContent;
	private int count;
	private String createDate;
	private String status;
	private int boardType;
	private String approveStatus;
	private String boardFileUrl;

}
