package com.dev.forest.member.model.dto;

import java.util.Date;

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
public class MemberDTO {
	
	private Long userNo;
	private String signUp;
	private String userId;
	private String userPwd;
	private String nickname;
	private String role;
	private Date enrollDate;
	private Date updateDate;
	private String status;

}
