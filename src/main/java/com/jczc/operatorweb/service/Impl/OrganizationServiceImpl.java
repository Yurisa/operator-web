package com.jczc.operatorweb.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jczc.operatorweb.dao.OrganizationDao;
import com.jczc.operatorweb.entity.Organization;
import com.jczc.operatorweb.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Override
	public Integer addOrganization(Organization organization) {
		Date createTime = new Date();
		organization.setCreateTime(createTime);
		return organizationDao.save(organization);
	}
	
	@Override
	public Organization getOrganizationById(Integer id) {
		return organizationDao.queryOrganizationById(id);
	}
}
