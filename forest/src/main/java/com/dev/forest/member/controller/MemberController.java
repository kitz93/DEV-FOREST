package com.dev.forest.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.member.model.dto.LoginMember;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="members", produces="application/json; charset=UTF-8")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final AuthenticationService authService;
	
	@PostMapping
	public ResponseEntity<String> saveMember(@RequestBody MemberDTO member) {
		// log.info("member = {}", member);
		memberService.saveMember(member);
		return ResponseEntity.ok("회원 가입 성공");
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginMember> login(@RequestBody MemberDTO member) {
		Map<String, String> tokens = authService.login(member);
		LoginMember loginMember = LoginMember.builder().username(member.getUserId()).tokens(tokens).build();
		return ResponseEntity.ok(loginMember);
	}

}
