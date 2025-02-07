package com.dev.forest.auth.model.service;

import java.util.Map;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.member.model.dto.MemberDTO;

public interface AuthenticationService {

	Map<String, String> login(MemberDTO member);
	
	CustomUserDetails getAuthenticatedUser();
	
	void validWriter(String writer, String username);

}
