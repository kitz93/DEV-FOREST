package com.dev.forest.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyPageDTO {
	
	private Long userNo;
	private String signUp;
	private String userId;
	private String userPwd;
	private String snsId;
	private String nickname;
	private String role;
	private String status;
	private String totalCount;
	private String correctCount;
	private String wrongCount;
	

}
