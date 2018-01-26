package com.jczc.operatorweb.controller.rest;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.entity.PileStation;
import com.jczc.operatorweb.entity.Position;
import com.jczc.operatorweb.model.StationAndPosition;
import com.jczc.operatorweb.service.PileStationService;
import com.jczc.operatorweb.util.ResponseModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("station")
public class PileStationController {

    @Autowired
    PileStationService pileStationService;

    Integer operatorId = 1;

    @RequestMapping({"","/"})
    public ResponseModel listOperatorAllStation(HttpSession session,
                                                @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                                @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true,"",pileStationService.getAllPileStationsByOperatorId(operatorId, pageNum, pageSize));
    }
    @RequestMapping("/stat/{id}")
    public ResponseModel listOperatorAllStation(@PathVariable("id")Integer id){
        return new ResponseModel<>(true,"",pileStationService.getStationStatById(id));
    }
    @RequestMapping("/message/{stationId}")
    public ResponseModel getStationMessage(@PathVariable("stationId") Integer stationId){
        return new ResponseModel<>(true, "", pileStationService.getPileStationMessageById(stationId));
    }

    @RequestMapping("/areaAndStations")
    public ResponseModel getAllAreaAndStations(HttpSession session){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true,"",pileStationService.getAllAreaAndStations(operatorId));
    }

    @PostMapping({"/",""})
    public ResponseModel addPileStation(HttpSession session,
                                        StationAndPosition stationAndPosition){
        System.out.println(JSON.toJSONString(stationAndPosition));
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        stationAndPosition.setCreatorId(operatorId);
        stationAndPosition.setOperatorId(operatorId);
        return new ResponseModel(true, "", pileStationService.addPileStationAndPosition(stationAndPosition));
    }

    @GetMapping("/{stationId}")
    public ResponseModel getStationById(@PathVariable Integer stationId){
        return new ResponseModel<>(true, "", pileStationService.getStationById(stationId));
    }

    @PostMapping("/{stationId}")
    public ResponseModel updateStation(@PathVariable Integer stationId,
                                       StationAndPosition stationAndPosition){
        return new ResponseModel<>(true, "", pileStationService.updateStationAndPosition(stationAndPosition));
    }
    @GetMapping("/nearbyStation")
    public ResponseModel listStationByLngLat(@RequestParam("gpsLng") double gpsLng,
                                             @RequestParam("gpsLat") double gpsLat){
        return new ResponseModel<>(true, "", pileStationService.getStationByLngLat(gpsLng, gpsLat));

    }

    @DeleteMapping("/{stationId}")
    public ResponseModel removeStation(@PathVariable Integer stationId){
        return new ResponseModel<>(true, "", pileStationService.removeStationById(stationId));
    }

//    @PostMapping("/stationStatistics/{areaId}")
//    public ResponseModel listStationStatistics(@PathVariable Integer areaId,@RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
//                                               @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize,Date startTime,Date endTime,String stationName){
//        return new ResponseModel<>(true, "", pileStationService.findStationStatisticsByAreaId(areaId,pageNum,pageSize,startTime,endTime,stationName));
//    }
}
