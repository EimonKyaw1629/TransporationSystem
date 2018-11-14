package com.example.demo.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.TransportationDAO;
import com.example.demo.Service.TransportationService;
import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;

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
			
			String cost= service.getFare(departure, arrive);	
			String tmp = cost.replace("円", "");
			int fare = Integer.parseInt(tmp);
			return fare;
			
		} catch(Exception e) {
			return -1;
		}
    }
	
	@RequestMapping(value="/deleteItem/",  method = RequestMethod.POST)
    public int test(Model m,@RequestParam(value = "fareID" , required = false) int departure,@RequestParam(value = "personID" , required = false) int pid, ModelMap model) throws IOException
    {
		try {
			dao.deleteFaleInfo(departure);
			/*
			List<DutyPersonInfo> dutyInfo = dao.dutyFindAll();
			m.addAttribute("dutyInfo", dutyInfo);
			
			int totalcost=0;
			FareInfo info = new FareInfo();
			info.setPersonID(pid);
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
			*/
		System.out.println(departure);
		System.out.println(pid);
			return 1;
			
		} catch(Exception e) {
			return -1;
		}
    }
}
