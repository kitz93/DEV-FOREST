package com.dev.forest.member.model.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.exception.DupplicatedUserException;
import com.dev.forest.exception.InvalidParameterException;
import com.dev.forest.exception.MissmatchPasswordException;
import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.MyPageDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void saveMember(MemberDTO member) {
		if (member != null) {
			if ("".equals(member.getUserId()) || "".equals(member.getUserPwd()) || "".equals(member.getNickname())) {
				throw new InvalidParameterException("유효하지 않은 값입니다.");
			}
			MemberDTO searchedByUserId = memberMapper.findByUserId(member.getUserId());
			if (searchedByUserId != null) {
				throw new DupplicatedUserException("이미 가입한 아이디입니다.");
			}
			MemberDTO searchedByNickname = memberMapper.findByNickname(member.getNickname());
			if (searchedByNickname != null) {
				throw new DupplicatedUserException("중복된 닉네임입니다.");
			}
			MemberDTO requestMember = MemberDTO.builder().signUp(member.getSignUp()).userId(member.getUserId())
					.userPwd(passwordEncoder.encode(member.getUserPwd())).nickname(member.getNickname()).build();
			memberMapper.saveMember(requestMember);
		}
	}

	@Override
	public void saveSnsMember(SnsMemberDTO member) {
		if (member != null) {
			if ("".equals(member.getNickname())) {
				throw new InvalidParameterException("유효하지 않은 값입니다.");
			}
			SnsMemberDTO searchedBySnsId = memberMapper.findBySnsId(member.getSnsId());
			if (searchedBySnsId != null) {
				throw new DupplicatedUserException("이미 가입한 아이디입니다.");
			}
			SnsMemberDTO searchedByNickname = memberMapper.findByNicknameSns(member.getNickname());
			if (searchedByNickname != null) {
				throw new DupplicatedUserException("중복된 닉네임입니다.");
			}
			memberMapper.saveSnsMember(member);
		}
	}

	@Override
	public void update(ChangePwdDTO changePwd) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		if (!passwordEncoder.matches(changePwd.getCurrPwd(), user.getPassword())) {
			throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		String newPwd = passwordEncoder.encode(changePwd.getNewPwd());
		ChangePwdDTO request = ChangePwdDTO.builder().userNo(user.getUserNo()).newPwd(newPwd).build();
		memberMapper.update(request);
	}

	@Override
	public void delete(String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		memberMapper.delete(user.getUserNo());
	}
	
	@Override
	public void snsDelete() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		memberMapper.delete(user.getUserNo());
	}

	@Override
	public MyPageDTO myPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		MyPageDTO response = memberMapper.findInfoByUserNo(user);
		return response;
	}

}
