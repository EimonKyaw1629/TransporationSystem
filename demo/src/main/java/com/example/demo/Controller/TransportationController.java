package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DAO.TransportationDAO;
import com.example.demo.Service.TransportationService;
import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;


@Controller
public class TransportationController {

	@Autowired
	private TransportationService tService;
	
	@Autowired
	private TransportationDAO dao;
	
	public ModelAndView getLoginInfo(String vname)
	{
		ModelAndView modelAndView = new ModelAndView();
		
	    modelAndView.setViewName(vname);
	    return modelAndView;
	}

	@RequestMapping(value={ "/insert" }, method = RequestMethod.POST)//Register
	public String index(@ModelAttribute FareInfo info, @ModelAttribute DutyPersonInfo dutyPersonInfo, Model m)
	{
		
		System.out.println(dutyPersonInfo);
		System.out.println(info);
		
		dao.insert(info);
		
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
