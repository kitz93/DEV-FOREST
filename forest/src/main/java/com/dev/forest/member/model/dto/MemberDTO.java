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
public class MemberDTO {
	
	private Long userNo;
	private String signUp;	
	
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]*$", message = "아이디는 이메일 형식만 사용 가능합니다.")
	@NotBlank(message = "아이디는 반드시 입력해야 합니다.")
	private String userId;	
	
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_]).+$", message = "비밀번호는 영문자 또는 숫자, 특수문자를 모두 포함해야 합니다.")
	@Size(min = 8, message = "비밀번호는 최소 8글자 이상만 사용 가능합니다.")
	@NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
	private String userPwd;
	
	@Pattern(regexp = "^[A-Za-z0-9가-힣]*$", message = "별명에는 특수문자 사용이 불가능합니다.")
	@Size(min = 2, message = "닉네임은 최소 2글자 이상만 사용 가능합니다.")
	@NotBlank(message = "별명은 반드시 입력해야 합니다.")
	private String nickname;
	
	private String role;
	private Date enrollDate;
	private Date updateDate;
	private String status;

}
