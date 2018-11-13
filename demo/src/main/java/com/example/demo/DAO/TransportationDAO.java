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
    
    public List<Map<String,Object>> getSearchList(FareInfo info)
    {
       String sql= null;
       String endDate  = info.getEndDate();
       String startDate = info.getUseDate();
       String AndOr =null;
       
       
       if(info.getPersonID()==0) {
    	   AndOr = "or";
    	   if((startDate == null && endDate == null)||(startDate == "" && endDate == "")) {
    		   startDate = null;
               endDate = null;
    	   } else {
    		   if (info.getEndDate() == ""){
    			   endDate="'9999-01-01'";
                   startDate = "'"+startDate+"'";
    		   }else {
    			   endDate="'"+endDate+"'";
                   startDate = "'"+startDate+"'";
    		   }
    	   }
       } else {
    	   AndOr = "and";
           if( info.getEndDate() == "") {
        	   endDate = "'9999-01-01'";
               startDate = "'"+startDate+"'";
           } else {
        	   endDate = "'"+endDate+"'";
               startDate = "'"+startDate+"'";
           }
       }
       
       sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
               "FROM Tb_Fare" +
               " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
             +" where (Tb_Fare.PersonID =IIF("+info.getPersonID()+ " IS NULL, Tb_Fare.PersonID, "+ info.getPersonID()+ ")) "+ AndOr +
               " Tb_Fare.UseDate BETWEEN (ISNULL("+startDate+",Tb_Fare.UseDate)) " +
                       "  AND (ISNULL( "+ endDate+",Tb_Fare.UseDate))";
       
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        System.out.println(list);
        return list;
    }
}