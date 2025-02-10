package com.dev.forest.member.model.service;

import com.dev.forest.member.model.dto.ChangePwdDTO;
import com.dev.forest.member.model.dto.MemberDTO;

public interface MemberService {

	void saveMember(MemberDTO member);

	void update(ChangePwdDTO changePwd);

	void delete(String password);

}
