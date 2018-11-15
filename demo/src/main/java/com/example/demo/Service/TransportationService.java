package com.example.demo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TransportationInfo;


@Repository

public class TransportationService {
	
	public List<TransportationInfo> getFare(String starteki,String endeki) throws IOException
    {
		try {
			StringBuilder str = new StringBuilder();
			ArrayList<String> strtmp = new ArrayList<String>();
			ArrayList<ArrayList<String>> GroupList = new ArrayList<ArrayList<String>>();
			ArrayList<Elements> StationList = new ArrayList<Elements>();

	        List<TransportationInfo> trInfo = new ArrayList<TransportationInfo>();
	        ArrayList<String> fare = new ArrayList<String>();
	        ArrayList<String> timer = new ArrayList<String>();
	        ArrayList<String> transfer = new ArrayList<String>();
	        
	        String webURL = "https://transit.yahoo.co.jp/search/result?from="+starteki+"&to="+endeki;
	        Document document = Jsoup.connect(webURL).get();
	        
	        Elements rsltlst=document.select("#rsltlst");
	        Elements rsltlst_fare=rsltlst.select(".fare");
	        Elements rsltlst_time=rsltlst.select(".time");
	        Elements rsltlst_transfer=rsltlst.select(".transfer");
	        
	        for (int i = 1 ; i <= 3 ; i++) {
	        	StationList.add(document.select("#route0"+i).select(".station"));
	        }
	        
	        for (Elements st : StationList) {
	        	ArrayList<String> StationTmp = new ArrayList<String>();
	        	for (Element breadCrumb : st) {
		            String result = breadCrumb.text().replace("時刻表 出口 地図", "");
		            result = result.replace("時刻表 地図", "");
		            result = result.replace("ホテル", "");
		            result = result.replace("[dep]", "");
		            result = result.replace("[train]", "");
		            result = result.replace("[arr]", "");
		            StationTmp.add(result);
		        }
	        	GroupList.add(StationTmp);
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
	        
	        for (ArrayList<String> list : GroupList) {
        		for (String station : list) {
	        		str.append(station+" → ");
	        	}
        		strtmp.add(str.toString().substring(0, str.lastIndexOf("→")));
        		str.setLength(0);
        	}
	        
	        for (int i = 0 ; i <= 2 ; i++) {
	        	TransportationInfo tmp = new TransportationInfo();
	        	tmp.setFare(fare.get(i));
	        	tmp.setTime(timer.get(i));
	        	tmp.setTransfer(transfer.get(i));
	        	tmp.setStation(strtmp.get(i));
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
