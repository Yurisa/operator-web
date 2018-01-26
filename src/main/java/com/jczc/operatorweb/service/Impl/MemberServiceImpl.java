package com.jczc.operatorweb.service.Impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jczc.operatorweb.dao.MemberDao;
import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public Member CheckLoginInfo(Member member) {

		return memberDao.queryLoginInfo(member);
	}
	
	@Override
	public Integer addMember(Member member) {
		Date createTime = new Date();
		member.setCreateTime(createTime);
		return memberDao.save(member);
	}
}
