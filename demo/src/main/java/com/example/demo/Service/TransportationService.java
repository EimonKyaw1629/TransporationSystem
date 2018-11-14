package com.example.demo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.FareInfo;
import com.example.demo.model.TransportationInfo;

@Component
@Repository
@Transactional
public class TransportationService  extends JdbcDaoSupport{

	@Autowired
	public TransportationService(DataSource ds)
	{
		this.setDataSource(ds);
	}
	
	public List<Map<String, Object>> getFareInfo()
	{
		String sql ="Select * from Tb_Fare";
		  List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		  return list;
	}
	
	public void insertFareInfo(FareInfo info)
	{
		String sql = "Insert into Tb_Fare(PersonID,UseDate,Departure_Station,Arrival_Station,Purpose,Fare)Values(?,?,?,?,?,?)";
		Object[] params = new Object[] {info.getPersonID(),info.getUseDate(),info.getDepartureStation(),info.getArrivalStation(),
				info.getPurpose(),info.getFare()};
		getJdbcTemplate().update(sql,params);
	}
	
	public List<TransportationInfo> getFare(String starteki,String endeki) throws IOException
    {
		try {
	        List<TransportationInfo> trInfo = new ArrayList<TransportationInfo>();
	        ArrayList<String> fare = new ArrayList<String>();
	        ArrayList<String> timer = new ArrayList<String>();
	        ArrayList<String> transfer = new ArrayList<String>();
	        ArrayList<String> transport = new ArrayList<String>();
	        String webURL = "https://transit.yahoo.co.jp/search/result?from="+starteki+"&to="+endeki;
	        Document document = Jsoup.connect(webURL).get();
	        
	        Elements rsltlst=document.select("#rsltlst");
	        Elements rsltlst_fare=rsltlst.select(".fare");
	        Elements rsltlst_time=rsltlst.select(".time");
	        Elements rsltlst_transfer=rsltlst.select(".transfer");
	        Elements rsltlst_transport=document.select(".transport");
	        
	        for (Element breadCrumb : rsltlst_transport) {
	            String result = breadCrumb.text().replace("[line] [train]", "");
	            result = result.replace("[line] [walk]", "");
	            transport.add(result);
	        }
	        
	        for (Element breadCrumb : rsltlst_fare) {
	            String result = breadCrumb.text().replace("円", "");
	            result = result.replace(",", "");
	            fare.add(result);
	        }
	        
	        for (Element breadCrumb : rsltlst_time) {
	            String result = breadCrumb.text().replace("[!]", "");
	            result = new StringBuffer(result).insert(11, " ").toString();
	            timer.add(result);
	        }
	        
	        for (Element breadCrumb : rsltlst_transfer) {
	            String result = breadCrumb.text();
	            transfer.add(result);
	        }
	        
	        for (int i = 0 ; i <= 2 ; i++) {
	        	TransportationInfo tmp = new TransportationInfo();
	        	tmp.setFare(fare.get(i));
	        	tmp.setTime(timer.get(i));
	        	tmp.setTransfer(transfer.get(i));
	        	tmp.setTransport(transport.get(i));
	        	trInfo.add(i, tmp);
	        }
	        
	        System.out.println(trInfo);
	        return trInfo;
        
		} catch (Exception e) {
			e.toString();
			return null;
		}
    }
}
