package com.jczc.operatorweb.controller.rest;

import com.jczc.operatorweb.entity.GroupType;
import com.jczc.operatorweb.service.GroupTypeService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groupType")
public class GroupTypeController {

    @Autowired
    GroupTypeService groupTypeService;

    @GetMapping("/{groupTypeId}")
    public ResponseModel getById(@PathVariable Integer groupTypeId){
        return new ResponseModel<>(true, "", groupTypeService.getById(groupTypeId));
    }
}
