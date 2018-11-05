package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Service.TransportationService;
import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;

@Controller
public class TransportationController {

	@Autowired
	private TransportationService tService;
	
	
	@RequestMapping(value={ "/insert" }, method = RequestMethod.POST)//Register
	public String index(@ModelAttribute FareInfo info, @ModelAttribute DutyPersonInfo dutyPersonInfo, Model m) 
	{
		System.out.println(dutyPersonInfo);
		System.out.println(info);
		
		return "TransportationList";
	}
	
	@RequestMapping(value={ "/insert" },params = "search", method = RequestMethod.POST)
	public String cancelUpdateUser(@ModelAttribute FareInfo info, BindingResult result, Model m) throws IOException {
		String cost= tService.getFare(info.getDepartureStation(),info.getArrivalStation());	
		m.addAttribute("DepartureStation",info.getDepartureStation());
		m.addAttribute("ArrivalStation",info.getArrivalStation());
		 m.addAttribute("Fare",cost);
		return "FrmTransportation";
	   
	}
	
	@RequestMapping(value={ "/" })//Register
	public String register()
	{
		return "FrmTransportation";
	}
	
	@RequestMapping(value="/TransportationList",  method = RequestMethod.GET)//Register
	public String getListPage()
	{
		
	    return "TransportationList";
	}
	
}
