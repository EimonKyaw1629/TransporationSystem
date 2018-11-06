package com.example.demo.Controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	


	/*@RequestMapping(value={ "/insert" }, method = RequestMethod.POST)
	public String index(@ModelAttribute FareInfo info, @ModelAttribute DutyPersonInfo dutyPersonInfo, Model m)
	{
		System.out.println(dutyPersonInfo);
		System.out.println(info);

		dao.insert(info);
		return "TransportationList";
	}*/
	

	@RequestMapping(value={ "/insert" },params = "search", method = RequestMethod.POST) 
	public String cancelUpdateUser(@ModelAttribute FareInfo info, BindingResult result, Model m) throws IOException {
		
		String cost= tService.getFare(info.getDepartureStation(),info.getArrivalStation());	
		m.addAttribute("UseDate",info.getUseDate());
		m.addAttribute("Purpose", info.getPurpose());
		m.addAttribute("DepartureStation",info.getDepartureStation());
		m.addAttribute("ArrivalStation",info.getArrivalStation());
		m.addAttribute("Fare",cost);
		return "FrmTransportation";
	   
	}
	
	@RequestMapping(value={ "/" }, method = RequestMethod.GET)
	public String register(Model m) throws JsonProcessingException
	{
		List<DutyPersonInfo> dutyInfo = dao.DutyfindAll();
		m.addAttribute("dutyInfo", dutyInfo);
		return "FrmTransportation";
	}
	
	@RequestMapping(value="/TransportationList",  method = RequestMethod.GET)
	public String getListPage()
	{
		
	    return "TransportationList";
	}
	
}
