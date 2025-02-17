package com.dev.forest.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.LoginMemberDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.MyPageDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;
import com.dev.forest.member.model.service.MemberService;

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
		return ResponseEntity.ok("사이트 회원 가입 성공");
	}
	
	@PostMapping("sns")
	public ResponseEntity<String> saveSnsMember(@Valid @RequestBody SnsMemberDTO member) {
		memberService.saveSnsMember(member);
		return ResponseEntity.ok("소셜 회원 가입 성공");
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginMemberDTO> login(@RequestBody MemberDTO member) {
		LoginMemberDTO loginMember = authService.login(member);
		return ResponseEntity.ok(loginMember);
	}
	
	@PostMapping("snsLogin")
	public ResponseEntity<LoginMemberDTO> login(@RequestBody SnsMemberDTO member) {
		LoginMemberDTO loginMember = authService.snsLogin(member);
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
	
	@GetMapping("myPage")
	public ResponseEntity<MyPageDTO> myPage() {
		MyPageDTO response = memberService.myPage();
		return ResponseEntity.ok(response);
	}

}
