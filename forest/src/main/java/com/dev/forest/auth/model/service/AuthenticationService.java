package com.dev.forest.auth.model.service;

import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;

public interface AuthenticationService {

	LoginMemberDTO login(MemberDTO member);

}
