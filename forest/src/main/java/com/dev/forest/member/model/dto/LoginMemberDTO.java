package com.dev.forest.member.model.dto;

import java.util.Map;

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
public class LoginMemberDTO {
	
	private String username;
	private String nickname;
	private Map<String, String> tokens;

}
