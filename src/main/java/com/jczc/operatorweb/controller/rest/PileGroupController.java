package com.jczc.operatorweb.controller.rest;

import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.model.GroupAndPositon;
import com.jczc.operatorweb.model.GroupRequireMent;
import com.jczc.operatorweb.service.PileGroupService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.acl.Group;

@RestController
@RequestMapping("group")
public class PileGroupController {

    @Autowired
    PileGroupService pileGroupService;

    Integer operatorId = 1;

   @GetMapping("/message/{groupId}")
    public ResponseModel getGroupMessage(@PathVariable("groupId") Integer groupId){
       return new ResponseModel<>(true,"",pileGroupService.getPileGroupMessageById(groupId));
   }

   @GetMapping("/resource")
   public ResponseModel listOperatorAllGroupResource(HttpSession session){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getOperatorAllGroupResource(operatorId));
   }

   @GetMapping("/station/{stationId}")
    public ResponseModel listPileGroupByStationId(HttpSession session,
                                                  @PathVariable Integer stationId,
                                                  @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                  @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
       operatorId = ((Member)session.getAttribute("member")).getOrgId();
       return new ResponseModel<>(true,"",pileGroupService.getPileGroupByStationId(operatorId, stationId, pageNum, pageSize));
   }

   @GetMapping("/zone/{zoneId}")
    public ResponseModel listPileGroupByZoneId( HttpSession session,
                                                @PathVariable Integer zoneId,
                                                @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
       operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "" , pileGroupService.getPileGroupByZoneId(operatorId, zoneId, pageNum, pageSize));
   }

   @GetMapping("/city/{cityId}")
    public ResponseModel listPileGroupByCityId(HttpSession session,
                                               @PathVariable Integer cityId,
                                               @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                               @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getPileGroupByCityId(operatorId, cityId, pageNum, pageSize));
   }

   @GetMapping("/province/{provinceId}")
    public ResponseModel listPileGroupByProvinceId(HttpSession session,
                                                   @PathVariable Integer provinceId,
                                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                   @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getPileGroupByProvinceId(operatorId, provinceId, pageNum, pageSize));
   }

   @GetMapping("/stationMessage/{stationId}")
    public ResponseModel getStationMessage(HttpSession session,
                                           @PathVariable Integer stationId){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getGroupStationMessageByStationId(operatorId, stationId));
   }

   @GetMapping("/zoneMessage/{zoneId}")
   public ResponseModel getZoneMessage(HttpSession session,
                                       @PathVariable Integer zoneId){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getGroupZoneMessageByZoneId(operatorId, zoneId));
   }

   @GetMapping("/cityMessage/{cityId}")
    public ResponseModel getCityMessage(HttpSession session,
                                        @PathVariable Integer cityId){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getGroupCityMessageByCityId(operatorId, cityId));
   }

   @GetMapping("/provinceMessage/{provinceId}")
    public ResponseModel getProvinceMessage(HttpSession session,
                                            @PathVariable Integer provinceId){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getGroupProvinceMessageByProvinceId(operatorId, provinceId));
   }

   @GetMapping("/requireMent")
    public  ResponseModel listGroupByRequirement(HttpSession session,
                                                 GroupRequireMent groupRequireMent,
                                                 @RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                                 @RequestParam(defaultValue = "8", name = "pageSize") int pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", pileGroupService.getGroupByRequirement(operatorId, groupRequireMent, pageNum, pageSize));
   }

   @PostMapping("/addPileGroup")
    public ResponseModel addPileGroup(HttpSession session,
                                      GroupAndPositon groupAndPositon) {
       operatorId = ((Member)session.getAttribute("member")).getOrgId();
        groupAndPositon.setOperatorId(operatorId);
        groupAndPositon.setCreatorId(operatorId);
        return new ResponseModel<>(true, "", pileGroupService.addPileGroupAndPositon(groupAndPositon));
   }

   @GetMapping("/{groupId}")
    public ResponseModel getGroupById(@PathVariable Integer groupId) {
        return new ResponseModel<>(true, "", pileGroupService.getGroupById(groupId));
    }

    @PostMapping("/{groupId}")
    public ResponseModel updateGroup(@PathVariable Integer groupId, GroupAndPositon groupAndPositon) {
        return new ResponseModel<>(true,"",pileGroupService.updatePileGroupAndPosition(groupAndPositon));
    }
}
