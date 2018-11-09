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
	private TransportationService tService;
	
	@Autowired
	private TransportationDAO dao;

	@RequestMapping(value={ "/insert" },params = "search", method = RequestMethod.POST) 
	public String cancelUpdateUser(@ModelAttribute FareInfo info,BindingResult result, Model m) throws IOException {
		
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		if(info.getArrivalStation()!="" && info.getDepartureStation()!="")
		{
			String cost= tService.getFare(info.getDepartureStation(),info.getArrivalStation());	
			String tmp = cost.replace("円", "");
			info.setFare(Integer.parseInt(tmp));
		}

		m.addAttribute("finfo",info);
		return "FrmTransportation";
	}
	
	@RequestMapping(value={ "/insert" },params = "create", method = RequestMethod.POST) 
	public String UpdateUser(@ModelAttribute FareInfo info, BindingResult result, @RequestParam String Fare, Model m) throws IOException {
		String tmp = Fare.replace("円", "");
		info.setFare(Integer.parseInt(tmp));
	    System.out.println(info);
		dao.insert(info);
		return "redirect:/TransportationList";
	}

	
	@RequestMapping(value={ "/" }, method = RequestMethod.GET)
	public String register(Model m,@ModelAttribute FareInfo info, BindingResult result) throws JsonProcessingException {
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		m.addAttribute("finfo", info);
		return "FrmTransportation";
	}
	
	@RequestMapping(value="/TransportationList",  method = RequestMethod.GET)
	public String getListPage(Model m) {
		int totalcost=0;
		List<Map<String, Object>> list=dao.getFareList();

		if(!list.isEmpty()) {
			for (Map<String, Object> k : list) {	
				totalcost += Integer.valueOf(k.get("Fare").toString());
			}
			m.addAttribute("Total",totalcost);
		}
		else {
			list= new ArrayList<Map<String, Object>>();
		}
		m.addAttribute("FareInfo", list);
	    return "TransportationList";
	}
	
	@RequestMapping(value="/back",  method = RequestMethod.POST)
	public String backPreviousPage(Model m,@ModelAttribute FareInfo info, BindingResult result)
	{
		List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		m.addAttribute("finfo", info);
		return "FrmTransportation";
	}
}
