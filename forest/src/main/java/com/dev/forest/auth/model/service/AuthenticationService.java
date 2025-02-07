package com.dev.forest.auth.model.service;

import java.util.Map;

import com.dev.forest.member.model.dto.MemberDTO;

public interface AuthenticationService {

	Map<String, String> login(MemberDTO member);

}
