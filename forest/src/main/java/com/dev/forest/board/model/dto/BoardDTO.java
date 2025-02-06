package com.dev.forest.board.model.dto;

import com.dev.forest.image.model.dto.ImageDTO;

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
	
	@NotBlank(message = "게시글 작성자는 비어있을 수 없습니다.")
	private String boardWriter;
	
	@NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
	private String boardTitle;
	
	@NotBlank(message = "게시글 내용은 비어있을 수 없습니다.")
	private String boardContent;
	private int count;
	private String createDate;
	private String status;
	private int boardType;
	private String approveStatus;
	
	private ImageDTO image;
	

}
