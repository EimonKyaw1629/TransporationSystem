package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TransportationService;
import com.example.demo.model.TransportationInfo;

@RestController
public class TransportationRestController {

	@Autowired
	private TransportationService service;
	
	@RequestMapping(value="/costitem/",  method = RequestMethod.POST)
    public int test(@RequestParam(value = "from" , required = false) String departure, @RequestParam(value = "to" , required = false) String arrive, ModelMap model) throws IOException
    {
		try {
			
			List<TransportationInfo> trInfo = service.getFare(departure, arrive);	
			String tmp = trInfo.get(0).getFare();
			int fare = Integer.parseInt(tmp);
			return fare;
			
		} catch(Exception e) {
			return -1;
		}
    }
}
