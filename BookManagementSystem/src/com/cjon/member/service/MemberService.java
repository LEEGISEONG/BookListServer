package com.cjon.member.service;

import com.cjon.member.dao.MemberDAO;

public class MemberService {


	public boolean insertMember(String id, String password) {
		MemberDAO dao = new MemberDAO();
		boolean result = dao.insert(id, password);
		
		return result;
	}

	public boolean loginMember(String id, String password) {
		MemberDAO dao = new MemberDAO();
		boolean result = dao.loging(id, password);
		
		return result;
	}

}
