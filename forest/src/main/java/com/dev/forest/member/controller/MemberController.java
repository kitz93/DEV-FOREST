package com.dev.forest.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.service.MemberService;
import com.dev.forest.token.model.dto.RefreshTokenDTO;
import com.dev.forest.token.model.service.TokenService;

import jakarta.validation.Valid;
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
	public ResponseEntity<String> saveMember(@Valid @RequestBody MemberDTO member) {
		memberService.saveMember(member);
		return ResponseEntity.ok("회원 가입 성공");
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginMemberDTO> login(@RequestBody MemberDTO member) {
		LoginMemberDTO loginMember = authService.login(member);
		return ResponseEntity.ok(loginMember);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@Valid @RequestBody ChangePwdDTO changePwd) {
		memberService.update(changePwd);
		return ResponseEntity.ok("비밀번호 수정 완료");
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Map<String, String> userPwd) {
		memberService.delete(userPwd.get("userPwd"));
		return ResponseEntity.ok("회원 탈퇴 완료");
	}

}
