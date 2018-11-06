package com.example.demo.DAO;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DutyPersonInfo;
import com.example.demo.model.FareInfo;

@Repository
public class TransportationDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<FareInfo> fareFindAll() {
        return this.jdbcTemplate.query("SELECT * FROM Tb_DutyPerson",
            (rs, rowNum) -> new FareInfo(rs.getInt("FareID"), rs.getInt("PersonID"), rs.getString("UseDate"),
            		rs.getString("Departure_station"), rs.getString("Arrival_station"), rs.getString("Purpose"), rs.getInt("Fare")));
    }
    
    public List<DutyPersonInfo> dutyFindAll() {
        return this.jdbcTemplate.query("SELECT * FROM Tb_DutyPerson",
            (rs, rowNum) -> new DutyPersonInfo(rs.getInt("PersonID"), rs.getString("PersonName")));
    }
    
	public void insert (FareInfo fareInfo) {
	     jdbcTemplate.update ("INSERT INTO Tb_Fare (PersonID, UseDate, Departure_station, Arrival_station, Purpose, Fare) VALUES (?,?,?,?,?,?)" ,
	    		 fareInfo.getPersonID(), fareInfo.getUseDate(), fareInfo.getDepartureStation(), fareInfo.getArrivalStation(), fareInfo.getPurpose(), fareInfo.getFare());
	}

	public void delete (FareInfo fareInfo) {
	     jdbcTemplate.update ( "DELETE FROM Tb_Fare WHERE PersonID =?" ,
	    		 fareInfo.getFareID());
	}
	
}
