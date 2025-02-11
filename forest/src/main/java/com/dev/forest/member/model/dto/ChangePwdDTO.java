package com.dev.forest.member.model.dto;

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
public class ChangePwdDTO {
	
	private Long userNo;
	private String currPwd;
	
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_]).+$", message = "비밀번호는 영문자 또는 숫자, 특수문자만 사용 가능합니다.")
	@Size(min = 8, message = "비밀번호는 최소 8자이상만 사용 가능합니다.")
	@NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
	private String newPwd;

}
