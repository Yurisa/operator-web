package com.jczc.operatorweb.dao;


import org.springframework.stereotype.Repository;
import com.jczc.operatorweb.entity.Member;

@Repository
public interface MemberDao {
	public Integer save(Member member);
	public Member queryLoginInfo(Member member);
}
