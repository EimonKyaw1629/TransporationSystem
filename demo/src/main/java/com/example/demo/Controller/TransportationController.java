package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Service.TransportationService;

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
	
	@RequestMapping(value="/Register")//Register
	
	public String index()
	{
		return "Register";
	}
}
