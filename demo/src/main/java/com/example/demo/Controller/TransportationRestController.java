package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TransportationService;

@RestController
public class TransportationRestController {

	@Autowired
	private TransportationService service;
	
	@RequestMapping(value="/costitem/",  method = RequestMethod.POST)
    public int test(@RequestParam(value = "from" , required = false) String departure, @RequestParam(value = "to" , required = false) String arrive, ModelMap model) throws IOException
    {
		try {
			
			String cost= service.getFare(departure, arrive);	
			String tmp = cost.replace("円", "");
			int fare = Integer.parseInt(tmp);
			return fare;
			
		} catch(Exception e) {
			return -1;
		}
    }
}
