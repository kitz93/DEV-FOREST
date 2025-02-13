package com.dev.forest.member.model.service;

import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.dto.SnsMemberDTO;

public interface MemberService {

	void saveMember(MemberDTO member);

	void saveSnsMember(SnsMemberDTO member);
	
	void update(ChangePwdDTO changePwd);

	void delete(String password);

	SnsMemberDTO snsLogin(SnsMemberDTO member);

}
