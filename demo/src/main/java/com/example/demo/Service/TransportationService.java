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


@Repository

public class TransportationService {

	
	public String getFare(String starteki,String endeki) throws IOException
    {
        String cost = null;
        ArrayList<String> fare = new ArrayList<String>();
        String webURL = "https://transit.yahoo.co.jp/search/result?from="+starteki+"&to="+endeki;
        Document document = Jsoup.connect(webURL).get();
        Elements breadCrumbs=document.select(".fare");
        for (Element breadCrumb : breadCrumbs) {
           
            String result = breadCrumb.text().replace("円", "");
            fare.add(result);
        }
        cost = fare.get(0);
        return cost;
    }
	
}
