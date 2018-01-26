package com.jczc.operatorweb.dao;

import org.springframework.stereotype.Repository;

import com.jczc.operatorweb.entity.Organization;

@Repository
public interface OrganizationDao {
	public Integer save(Organization organization);
	public Organization queryOrganizationById(Integer id);
}
