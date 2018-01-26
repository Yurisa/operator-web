package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.entity.GroupType;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupTypeDao {
    public GroupType queryById(Integer groupTypeId);
}
