package com.example.demo.DAO;


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

	public void deleteFaleInfo (int fareID) {
	     jdbcTemplate.update ( "DELETE FROM Tb_Fare WHERE FareID =?" ,
	    		 fareID);
	}
	
	public List<Map<String, Object>>  getFareList()
	{
		String sql = "Select UseDate,Departure_station,Arrival_station,Purpose,Fare,FareID," + 
				"SUBSTRING((SELECT ',' + PersonName  FROM Tb_DutyPerson" + 
				" where Tb_DutyPerson.PersonID=Tb_Fare.PersonID FOR XML PATH('')), 2, 999999) as PersonName" + 
				" from Tb_Fare";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	
	public List<Map<String,Object>> getSearchList(FareInfo info)
	{

		String sql= null;
		String defaultDate = info.getEndDate();
		if(defaultDate.equals("")) {
			defaultDate="9999-01-01";
		}
		sql ="Select UseDate,Departure_station,Arrival_station,Purpose,Fare,FareID, " + 
					"SUBSTRING((SELECT ',' + PersonName  FROM Tb_DutyPerson" + 
					" where Tb_DutyPerson.PersonID=Tb_Fare.PersonID FOR XML PATH('')), 2, 999999) as PersonName" + 
					" from Tb_Fare" + 
					" where (PersonID =IIF("+info.getPersonID()+ " IS NULL, PersonID, "+ info.getPersonID()+ ")) and "+
					"(UseDate between \'"+info.getUseDate()+"\' AND \'"+defaultDate+"\' ) ";
				
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	
	
}
