package com.dev.forest.auth.model.service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;

public interface AuthenticationService {

	LoginMemberDTO login(MemberDTO member);
	
	CustomUserDetails getAuthenticatedUser();
	
	void validWriter(String writer, String username);

	LoginMemberDTO snsLogin(SnsMemberDTO member);

}
