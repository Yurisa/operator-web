package com.jczc.operatorweb.controller.rest;

import com.jczc.operatorweb.entity.Area;
import com.jczc.operatorweb.service.AreaService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("area")
public class AreaController {
    @Autowired
    AreaService areaService;

    @RequestMapping({"","/"})
    public ResponseModel listAllProvince(){
        return new ResponseModel<>(true, "",  areaService.getAreaByParentId(0));
    }

    @RequestMapping("/{areaId}")
    public ResponseModel listArea(@PathVariable Integer areaId){
        return new ResponseModel<>(true, "",areaService.getAreaByParentId(areaId));
    }
    @RequestMapping("/getMyRegionId/{areaId}")
    public ResponseModel Area(@PathVariable Integer areaId){
        return new ResponseModel<>(true, "",areaService.getAreaById(areaId));
    }

}
