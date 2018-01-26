package com.jczc.operatorweb.service;

import com.jczc.operatorweb.entity.Organization;

public interface OrganizationService {
	public Integer addOrganization(Organization organization);
	public Organization getOrganizationById(Integer id);
}
