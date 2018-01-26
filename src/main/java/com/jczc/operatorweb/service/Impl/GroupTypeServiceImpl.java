package com.jczc.operatorweb.service.Impl;

import com.jczc.operatorweb.dao.GroupTypeDao;
import com.jczc.operatorweb.entity.GroupType;
import com.jczc.operatorweb.service.GroupTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupTypeServiceImpl implements GroupTypeService {
    @Autowired
    GroupTypeDao groupTypeDao;

    @Override
    public GroupType getById(Integer groupTypeId) {
        return groupTypeDao.queryById(groupTypeId);
    }
}
