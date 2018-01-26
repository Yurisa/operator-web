package com.jczc.operatorweb.controller.rest;

import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.entity.Member;
import com.jczc.operatorweb.entity.Pile;
import com.jczc.operatorweb.service.ChargingPriceService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("price")
public class ChargingPriceController {

    @Autowired
    ChargingPriceService chargingPriceService;

    Integer operatorId = -1;

    @GetMapping("/list")
    public ResponseModel listAllChargingPrice(HttpSession session,
                                              @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum,
                                              @RequestParam(defaultValue = "8", name = "pageSize") Integer pageSize){
      operatorId = ((Member)session.getAttribute("member")).getOrgId();
      return new ResponseModel<>(true, "", chargingPriceService.getAllChargingPrice(operatorId, pageNum, pageSize));
    }

    @GetMapping({"","/"})
    public ResponseModel listTotalChargingPrice(HttpSession session){
        operatorId = ((Member)session.getAttribute("member")).getOrgId();
        return new ResponseModel<>(true, "", chargingPriceService.getTotalChargingPrice(operatorId));
    }

    
    @PostMapping("/addAreaPrice")
    public ResponseModel addChargingPrice(ChargingPrice chargingprice){
        return new ResponseModel<>(true, "", chargingPriceService.addChargingPrice(chargingprice));
    }
    
    @DeleteMapping("/{chargingpriceId}")
    public ResponseModel removeChargingPriceById(
            @PathVariable Integer chargingpriceId
            ){
        return new ResponseModel<>(true, "", chargingPriceService.removeChargingPriceById(chargingpriceId));
    }
    
    @PostMapping("/{chargingpriceId}")
    public ResponseModel updateChargingPrice(@PathVariable Integer chargingpriceId,
                                    ChargingPrice chargingprice){
        return new ResponseModel<>(true,"",chargingPriceService.updateChargingPrice(chargingprice));
    }
}
