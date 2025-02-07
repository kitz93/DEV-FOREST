package com.dev.forest.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	void saveSignUp(MemberDTO member);

	void saveMember(MemberDTO member);
	
	void saveMemberSns(MemberDTO member);

	MemberDTO findByUserId(String userId);

	MemberDTO findByNickname(String nickname);

	MemberDTO findByNicknameSns(String nickname);

}
