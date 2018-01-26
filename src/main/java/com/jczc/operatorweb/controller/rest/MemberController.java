package com.jczc.operatorweb.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.service.MemberService;
import com.jczc.operatorweb.util.ResponseModel;

@RestController
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@PostMapping("/checkLoginInfo")
    public ResponseModel CheckLoginInfo(HttpSession session,HttpServletRequest request,String loginName,String password,Member member) {
		Member queryMember = memberService.CheckLoginInfo(member);
		loginName = request.getParameter("name");
		password = request.getParameter("password");
		if(queryMember!=null) {
			if(queryMember.getName().equals(loginName)&&queryMember.getPassword().equals(password)) {
				session.setAttribute("member", queryMember);
				return new ResponseModel<>(true, "", memberService.CheckLoginInfo(member));
			}else {
				return new ResponseModel<>(false, "信息不正确", memberService.CheckLoginInfo(member));
			}
		}else {
			return new ResponseModel<>(false, "信息不正确", memberService.CheckLoginInfo(member));
		}
		
        
    }
}
