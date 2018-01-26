package com.jczc.operatorweb.service;



import com.jczc.operatorweb.entity.Member;

public interface MemberService {
	public Integer addMember(Member member);
	public Member CheckLoginInfo(Member member);
}
