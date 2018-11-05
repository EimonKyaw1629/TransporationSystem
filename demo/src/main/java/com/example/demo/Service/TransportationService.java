package com.example.demo.Service;

import java.io.IOException;
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
	
	public String getFare(String starteki,String endeki) throws IOException
	{
		String cost = null;
		 String webURL = "https://transit.yahoo.co.jp/search/result?from="+starteki+"&to="+endeki;
		 Document document = Jsoup.connect(webURL).get();
         Elements costTitles = document.select(".fare");
         for (Element widgetTitle1 : costTitles) {
        	 	cost=widgetTitle1.text();
        }	
         return cost;
	}
	
}
