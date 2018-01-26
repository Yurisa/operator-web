package com.jczc.operatorweb.controller;

import com.jczc.operatorweb.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
	@RequestMapping("/homepage")
	public String homepage(){
		return "homepage";
	}

	@RequestMapping("/regist")
	public String regist(){
		return "regist";
	}

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/homepage";
    }

    @GetMapping(value = {"chargingPile","chargingPile/station/{stationId}","chargingPile/group/{groupId}"})
    public String charginPile(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
	    return "chargingPile";
    };

    @GetMapping("/addChargingPile/{groupId}")
    public String addChargingPile(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "addChargingPile";
    }

    @GetMapping("/updateChargingPile/{pileId}")
    public String updateChargingPile(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "updateChargingPile";
    }

    @GetMapping("/chargingPile/{id}")
    public String chargingPileInfo(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "checkChargingPile";
    }

    @GetMapping("/chargingStation")
    public String chargingStation(HttpSession session){
       Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingStation";
    }

    @GetMapping("/addChargingStation")
    public String addChargingStation(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "addChargingStation";
    }

    @GetMapping("/updateChargingStation/{stationId}")
    public String updateChargingStation(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "updateChargingStation";
    }

    @GetMapping("/chargingStation/{id}")
    public String chargingStationInfo(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "mapOfChargingStation";
    }

    @GetMapping("/chargingPileGroup")
    public String chargingPileGroup(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingPileGroup";
    }

    @GetMapping("/addChargingPileGroup/station/{stationId}/groupType/{groupTypeId}")
    public String addChargingPileGroup(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "addChargingPileGroup";
    }

    @GetMapping("/updateChargingPileGroup/{groupId}")
    public String updateChargingPileGroup(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "updateChargingPileGroup";
    }

    @GetMapping("/chargingPrice")
    public String chargingPrice(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingPrice";
    }

    @GetMapping("/chargingDetails")
    public String charingDetails(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingDetails"; }

    @GetMapping("/chargingStationStatistics")
    public String chargingStationStatistics(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingStationStatistics";
    }

    @GetMapping("/chargingPileStatistics")
    public String chargingPileStatistics(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingPileStatistics";
    }

    @GetMapping("/chargingPileStatistics/{stationId}")
    public String chargingPileStatisticsOfStation(HttpSession session){
        Member member = (Member)session.getAttribute("member");
        if (member == null){
            return "redirect:/homepage";
        }
        return "chargingPileStatistics";
    }

    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("member");
        return "redirect:/homepage";
    }
}
