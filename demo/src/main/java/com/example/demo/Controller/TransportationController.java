package com.example.demo.Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.TransportationDAO;
import com.example.demo.Service.TransportationService;
import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;
import com.fasterxml.jackson.core.JsonProcessingException;


@Controller
public class TransportationController {

	@Autowired
	private TransportationDAO dao;
	
	@Autowired
	private TransportationService service;

	@RequestMapping(value={ "/insert" },params = "search", method = RequestMethod.POST) 
	public String searchTransitCost(@ModelAttribute FareInfo info,BindingResult result, Model m) throws IOException {
		
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyPersonInfo", dutyInfo);
		if(info.getArrivalStation()!="" && info.getDepartureStation()!="")
		{
			String cost= service.getFare(info.getDepartureStation(),info.getArrivalStation());	
			String tmp = cost.replace("円", "");
			info.setFare(Integer.parseInt(tmp));
		}

		m.addAttribute("finfo",info);
		return "FrmTransportation";
	}
	
	@RequestMapping(value={ "/insert" },params = "create", method = RequestMethod.POST) 
	public String createTransitInfo(@ModelAttribute FareInfo info, BindingResult result, @RequestParam String Fare, Model m) throws IOException {
		String tmp = Fare.replace("円", "");
		info.setFare(Integer.parseInt(tmp));
	    System.out.println(info);
		dao.insert(info);
		return "redirect:/TransportationList";
	}
	
	@RequestMapping(value={ "/" }, method = RequestMethod.GET)
	public String appearTransitPage(Model m,@ModelAttribute FareInfo info, BindingResult result) throws JsonProcessingException {
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		System.out.println(dutyInfo);
		m.addAttribute("dutyPersonInfo", dutyInfo);
		m.addAttribute("finfo", info);
		return "FrmTransportation";
	}
	
	@RequestMapping(value="/TransportationList",  method = RequestMethod.GET)
	public String getListPage(Model m,@ModelAttribute FareInfo info) {
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		int totalcost=0;
		List<Map<String, Object>> list=dao.getSearchList(info);

		if(!list.isEmpty()) {
			for (Map<String, Object> k : list) {	
				totalcost += Integer.valueOf(k.get("Fare").toString());
			}
			m.addAttribute("Total",totalcost);
		}
		else {
			list= new ArrayList<Map<String, Object>>();
		}
		m.addAttribute("finfo",info);
		m.addAttribute("FareInfo", list);
	    return "TransportationList";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteTransitInfo(@RequestParam int id, Model m) {
		dao.deleteFaleInfo(id);
		return "redirect:/TransportationList";
	}
	
	@RequestMapping(value="/searchItem",  method = RequestMethod.POST)
	public String searchTransitItem(Model m,@ModelAttribute FareInfo info) {
		
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		int totalcost=0;
		List<Map<String, Object>> list=dao.getSearchList(info);
		if(!list.isEmpty()) {
			for (Map<String, Object> k : list) {	
				totalcost += Integer.valueOf(k.get("Fare").toString());
			}
			m.addAttribute("Total",totalcost);
		}
		else {
			
			list= new ArrayList<Map<String, Object>>();
		}
		m.addAttribute("finfo",info);
		m.addAttribute("FareInfo", list);
		return "TransportationList";
	}

}
