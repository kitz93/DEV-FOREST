package com.dev.forest.auth.model.service;

import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;

public interface AuthenticationService {

	LoginMemberDTO login(MemberDTO member);

	LoginMemberDTO snsLogin(SnsMemberDTO member);

}
