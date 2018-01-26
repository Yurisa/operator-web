package com.jczc.operatorweb.controller.rest;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.model.PileInfo;
import com.jczc.operatorweb.service.PileService;
import com.jczc.operatorweb.util.ResponseModel;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("pile")
@RestController
@Controller
public class PileController {
    HttpSession session;
    @Autowired
    PileService pileService;

    Integer operatorId = 1;

    @GetMapping({"", "/"})
    public ResponseModel listOperatorAllPiles(HttpSession session,
                                              @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                              @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize) {
         operatorId = ((Member)session.getAttribute("member")).getOrgId();
         return new ResponseModel<>(true,"",pileService.getOperatorAllPiles(operatorId, pageNum, pageSize));
    }
    @GetMapping("/{pileId}")
     public ResponseModel getPileById(@PathVariable Integer pileId){
        return new ResponseModel<>(true, "", pileService.getPileById(pileId));
    }
    @GetMapping("/no/{pileNo}")
    public ResponseModel getPileByNo(@PathVariable String pileNo){
       return new ResponseModel<>(true, "", pileService.getPileByNo(pileNo));
   }

    @PostMapping({"", "/"})
    public ResponseModel addPile(Pile pile){
        return new ResponseModel<>(true, "", pileService.addPile(pile));
    }
    @GetMapping("/station/{stationId}")
    public ResponseModel listPileByBuildId(
            @PathVariable Integer stationId,
            @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "8", name = "pageSize") Integer pagesize) {
        return new ResponseModel<>(true,"",pileService.getPileByBuildId(stationId, pageNum, pagesize));
    }

    @GetMapping("/group/{groupId}")
    public ResponseModel listPileByGroupId(
            @PathVariable Integer groupId,
            @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "8", name = "pageSize") Integer pagesize) {
        return new ResponseModel<>(true,"",pileService.getPileByGroupId(groupId, pageNum, pagesize));
    }

    @DeleteMapping("/{pileId}")
    public ResponseModel removePileById(
            @PathVariable Integer pileId
            ){
        return new ResponseModel<>(true, "", pileService.removePileById(pileId));
    }

    @PostMapping("/{pileId}")
    public ResponseModel updatePile(@PathVariable Integer pileId,
                                    Pile pile){
        return new ResponseModel<>(true,"",pileService.updatePile(pile));
    }

    @GetMapping("/identification")
    public ResponseModel listAllPileIdentification(){
        return new ResponseModel<>(true, "", pileService.getAllPileIdentification());
    }

}
