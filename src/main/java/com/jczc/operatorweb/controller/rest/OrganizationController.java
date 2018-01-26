package com.jczc.operatorweb.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.entity.Organization;
import com.jczc.operatorweb.service.MemberService;
import com.jczc.operatorweb.service.OrganizationService;
import com.jczc.operatorweb.util.ResponseModel;

@RestController
@RequestMapping("organization")
public class OrganizationController {
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/regist")
	public ResponseModel organizationRegist(HttpSession session,HttpServletRequest request,Organization organization,Member member) {
		if(organizationService.getOrganizationById(organization.getId())==null) {
			organizationService.addOrganization(organization);
			if(organization.getId()!=null) {
				member.setCreatorId(organization.getId());
				member.setOrgId(organization.getId());
				memberService.addMember(member);
				session.setAttribute("member", member);
			}
			return new ResponseModel<>(true, "注册成功", null);
		}else {
			return new ResponseModel<>(false, "注册失败", null);
		}
		
		
		
	}
}
