package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	public ModelAndView getLoginInfo(String vname)
	{
		ModelAndView modelAndView = new ModelAndView();
		
	    modelAndView.setViewName(vname);
	    return modelAndView;
	}
	
	//@RequestMapping(value="/search/starteki= {starteki}&endeki= {endeki}")
	/*
	public ModelAndView deletePersonInfo(@PathVariable String starteki, @PathVariable String endeki,Model m) throws IOException {
	try
	{
		String cost=	tService.getFare(starteki, endeki);
		 ModelAndView modelAndView = this.getLoginInfo("FrmTransportation");
		
		return modelAndView;
	}
	catch(Exception e)
	{
		//return modelAndView;
	}
		
	}
	*/

	@RequestMapping(value={ "/insert" }, method = RequestMethod.POST)//Register
	public String index(@ModelAttribute FareInfo info, @ModelAttribute DutyPersonInfo dutyPersonInfo, Model m)
	{
		
		System.out.println(dutyPersonInfo);
		System.out.println(info);
		return "TransportationList";
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
