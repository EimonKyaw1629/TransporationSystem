package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.TransportationDAO;
import com.example.demo.Service.TransportationService;
import com.example.demo.model.TransportationInfo;

@RestController
public class TransportationRestController {

	@Autowired
	private TransportationService service;
	
	@Autowired
	private TransportationDAO dao;
	
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
	
	@RequestMapping(value="/deleteItem/",  method = RequestMethod.POST)
    public int test(@RequestParam(value = "fareID" , required = false) int departure,@RequestParam(value = "personID" , required = false) int pid, ModelMap model) throws IOException
    {
		try {
			dao.deleteFaleInfo(departure);
			System.out.println(departure);
			System.out.println(pid);
			return 1;
			
		} catch(Exception e) {
			return -1;
		}
    }
}
