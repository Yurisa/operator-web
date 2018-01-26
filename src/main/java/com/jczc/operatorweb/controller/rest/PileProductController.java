package com.jczc.operatorweb.controller.rest;

import com.jczc.operatorweb.entity.PileProduct;
import com.jczc.operatorweb.service.PileProductService;
import com.jczc.operatorweb.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class PileProductController {
    @Autowired
    PileProductService pileProductService;

    @GetMapping({"","/"})
    public ResponseModel listAllPileProduct(){
        return new ResponseModel<>(true,"",pileProductService.getAllPileProducts());
    }
}
