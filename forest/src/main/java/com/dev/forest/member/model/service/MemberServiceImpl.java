package com.dev.forest.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.forest.exception.DupplicatedUserException;
import com.dev.forest.exception.InvalidParameterException;
import com.dev.forest.member.model.dto.MemberDTO;
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
	@Transactional
	public void saveMember(MemberDTO member) {
		if (member != null && member.getSignUp().equals("사이트")) {
			if ("".equals(member.getUserId()) || "".equals(member.getUserPwd()) || "".equals(member.getNickname())) {
				throw new InvalidParameterException("유효하지 않은 값입니다.");
			}
			MemberDTO searchedByUserId = memberMapper.findByUserId(member.getUserId());
			if (searchedByUserId != null) {
				throw new DupplicatedUserException("중복된 아이디입니다.");
			}
			MemberDTO searchedByNickname = memberMapper.findByNickname(member.getNickname());
			MemberDTO searchedByNicknameSns = memberMapper.findByNicknameSns(member.getNickname());
			if (searchedByNickname != null || searchedByNicknameSns != null) {
				throw new DupplicatedUserException("중복된 닉네임입니다.");
			}
			MemberDTO requestMember = MemberDTO.builder().signUp(member.getSignUp()).userId(member.getUserId())
					.userPwd(passwordEncoder.encode(member.getUserPwd())).nickname(member.getNickname()).build();
			log.info("{}", requestMember);
			memberMapper.saveSignUp(requestMember);
			memberMapper.saveMember(requestMember);
		}
		if (member != null && member.getSignUp().equals("소셜")) {
			if ("".equals(member.getNickname())) {
				throw new InvalidParameterException("유효하지 않은 값입니다.");
			}
			MemberDTO searchedByNickname = memberMapper.findByNickname(member.getNickname());
			MemberDTO searchedByNicknameSns = memberMapper.findByNicknameSns(member.getNickname());
			if (searchedByNickname != null || searchedByNicknameSns != null) {
				throw new DupplicatedUserException("중복된 닉네임입니다.");
			}
			memberMapper.saveSignUp(member);
			memberMapper.saveMemberSns(member);
		}
	}

}
