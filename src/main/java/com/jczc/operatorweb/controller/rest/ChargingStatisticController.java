package com.jczc.operatorweb.controller.rest;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.model.*;
import com.jczc.operatorweb.service.ChargingStatisticService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/statistic")
public class ChargingStatisticController {

    @Autowired
    ChargingStatisticService chargingStatisticService;

    Integer operatorId = 1;

    @GetMapping("/station/zone/{zoneId}")
    public ResponseModel listStationStatisticByZoneId(HttpSession session,
                                                      @PathVariable Integer zoneId,
                                                      @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                      @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getStationStatisticByZoneId(operatorId, zoneId, pageNum, pageSize));
    }

    @GetMapping("/station/city/{cityId}")
    public ResponseModel listStationStatisticByCityId(HttpSession session,
                                                      @PathVariable Integer cityId,
                                                      @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                      @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getStationStatisticByCityId(operatorId, cityId, pageNum, pageSize));
    }

    @GetMapping("/station/province/{provinceId}")
    public ResponseModel listStationStatisticByProvinceId(HttpSession session,
                                                          @PathVariable Integer provinceId,
                                                          @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                          @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getStationStatisticByProvinceId(operatorId, provinceId, pageNum, pageSize));
    }

    @GetMapping("/station/requirement")
    public ResponseModel listStationStatisticByRequireMent(HttpSession session,
                                                           StationStatisticRequireMent stationStatisticRequireMent,
                                                           @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                           @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getStationStatisticByRequireMent(operatorId, stationStatisticRequireMent, pageNum, pageSize));
    }

    @GetMapping("/pile/station/{stationId}")
    public ResponseModel listPileStatisticByStationId(HttpSession session,
                                                      @PathVariable Integer stationId,
                                                      @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                      @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getPileStatisticByStationId(operatorId, stationId, pageNum, pageSize));
    }

    @GetMapping("/pile/zone/{zoneId}")
    public ResponseModel listPileStatisticByZoneId(HttpSession session,
                                                   @PathVariable Integer zoneId,
                                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                   @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getPileStatisticByZoneId(operatorId, zoneId, pageNum, pageSize));
    }

    @GetMapping("/pile/city/{cityId}")
    public ResponseModel listPileStatisticByCityId(HttpSession session,
                                                   @PathVariable Integer cityId,
                                                   @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                   @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getPileStatisticByCityId(operatorId, cityId, pageNum, pageSize));
    }

    @GetMapping("/pile/province/{provinceId}")
    public ResponseModel listPileStatisticByProvinceId(HttpSession session,
                                                       @PathVariable Integer provinceId,
                                                       @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                       @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getPileStatisticByProvinceId(operatorId, provinceId, pageNum, pageSize));
    }

    @GetMapping("/pile/requirement")
    public ResponseModel listPileStatisticByRequireMent(HttpSession session,
                                                        PileStatisticRequireMent pileStatisticRequireMent,
                                                        @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                        @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getPileStatisticByRequireMent(operatorId, pileStatisticRequireMent, pageNum, pageSize));
    }

    @GetMapping("/charging/station/{stationId}")
    public ResponseModel listChargingStatisticByStationId(HttpSession session,
                                                          @PathVariable Integer stationId,
                                                          @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                          @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getChargingStatisticByStationId(operatorId, stationId, pageNum, pageSize));
    }

    @GetMapping("/charging/zone/{zoneId}")
    public ResponseModel listChargingStatisticByZoneId(HttpSession session,
                                                       @PathVariable Integer zoneId,
                                                       @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                       @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getChargingStatisticByZoneId(operatorId, zoneId, pageNum, pageSize));
    }

    @GetMapping("/charging/city/{cityId}")
    public ResponseModel listChargingStatisticByCityId(HttpSession session,
                                                       @PathVariable Integer cityId,
                                                       @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                       @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getChargingStatisticByCityId(operatorId, cityId, pageNum, pageSize));
    }

    @GetMapping("/charging/province/{provinceId}")
    public ResponseModel listChargingStatisticByProvinceId(HttpSession session,
                                                           @PathVariable Integer provinceId,
                                                           @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                           @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getChargingStatisticByProvinceId(operatorId, provinceId, pageNum, pageSize));
    }

    @GetMapping("/charging/requirement")
    public ResponseModel listChargingStatisticByRequireMent(HttpSession session,
                                                            ChargingStatisticRequireMent chargingStatisticRequireMent,
                                                            @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                            @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingStatisticService.getChargingStatisticByRequireMent(operatorId, chargingStatisticRequireMent, pageNum, pageSize));
    }
}
