package com.dev.forest.board.model.dto;

import com.dev.forest.image.model.dto.ImageDTO;

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
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private int count;
	private String createDate;
	private String status;
	private int boardType;
	private String approveStatus;
	
	private ImageDTO image;
	

}
