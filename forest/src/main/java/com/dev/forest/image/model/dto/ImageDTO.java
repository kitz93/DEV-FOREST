package com.dev.forest.image.model.dto;

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
public class ImageDTO {
	
	private Long ImageNo;
	private Long refBno;
	private String originName;
	private String changeName;
	private String imagePath;
	private String updateDate;
	private String status;

}
