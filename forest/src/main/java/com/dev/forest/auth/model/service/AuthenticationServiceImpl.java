package com.dev.forest.auth.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.exception.DeleteMemberException;
import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public LoginMemberDTO login(MemberDTO member) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(member.getUserId(), member.getUserPwd()));
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		if(user != null && user.getStatus().equals("N")) {
			throw new DeleteMemberException("탈퇴한 유저 입니다.");
		}
		Map<String, String> tokens = tokenService.generatorToken(user.getUsername(), user.getUserNo());
		LoginMemberDTO loginMember = LoginMemberDTO.builder().username(user.getUsername()).nickname(user.getNickname()).tokens(tokens).build();
		return loginMember;
	}

}
