package com.dev.forest.member.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class SnsMemberDTO {
	
	private Long userNo;
	private String signUp;
	private String snsId;
	@Pattern(regexp = "^[A-Za-z0-9가-힣]*$", message = "별명에는 특수문자 사용이 불가능합니다.")
	@Size(min = 2, message = "닉네임은 최소 2글자 이상만 사용 가능합니다.")
	@NotBlank(message = "별명은 반드시 입력해야 합니다.")
	private String nickname;
	
	private String role;
	private Date enrollDate;
	private Date updateDate;
	private String status;

}
