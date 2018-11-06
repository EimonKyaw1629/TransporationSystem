package com.example.demo.DAO;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;

@Repository
public class TransportationDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<FareInfo> fareFind() {
    	
    	List<FareInfo> fareList = new ArrayList<FareInfo>();
        List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from Tb_Fare");
        
        for (Map<String, Object> map : list) {
        	FareInfo fareInfo = new FareInfo();
        	fareInfo.setFareID((int) map.get("FareID"));
        	fareInfo.setPersonID((int) map.get("PersonID"));
        	fareInfo.setUseDate(map.get("UseDate").toString());
        	fareInfo.setDepartureStation(map.get("Departure_station").toString());
        	fareInfo.setArrivalStation(map.get("Arrival_station").toString());
        	fareInfo.setPurpose(map.get("Purpose").toString());
        	fareInfo.setFare((int) map.get("Fare"));
            fareList.add(fareInfo);
        }
        return fareList;
    }
    
    public List<DutyPersonInfo> DutyfindAll() {
        return this.jdbcTemplate.query("SELECT PersonID, PersonName FROM Tb_DutyPerson",
            (rs, rowNum) -> new DutyPersonInfo(rs.getInt("PersonID"), rs.getString("PersonName")));
    }
    
	public void insert (FareInfo fareInfo) {
	     jdbcTemplate.update ("INSERT INTO Tb_Fare (PersonID, UseDate, Departure_station, Arrival_station, Purpose, Fare) VALUES (?,?,?,?,?,?)" ,
	             1, fareInfo.getUseDate(), fareInfo.getDepartureStation(), fareInfo.getArrivalStation(), fareInfo.getPurpose(), fareInfo.getFare());
	}

	public void delete (FareInfo fareInfo) {
	     jdbcTemplate.update ( "DELETE FROM book WHERE book_id =?" ,
	    		 fareInfo.getFareID());
	}
	
}
