package com.example.demo.DAO;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    
    
    public List<Map<String,Object>> getSearchList(FareInfo info)//
    {
        String sql= null;
        String endDate  = info.getEndDate();
        String startDate = info.getUseDate();
        if(info.getPersonID() == 0)
        {
 
            
            if( info.getEndDate() == "" && info.getUseDate() != "" ) {
                
                endDate="9999-01-01";
            }
            else if( info.getEndDate() == "" && info.getUseDate() != "" ) {

                startDate="1700-01-01";
            }
            
            if((startDate == null && endDate == null)||(startDate == "" && endDate == ""))
            {
                startDate = null;
                endDate = null;
                 sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                            "FROM Tb_Fare" +
                            " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                            +" where " +
                            "Tb_Fare.UseDate BETWEEN (ISNULL("+startDate+",Tb_Fare.UseDate)) " +
                                    "  AND (ISNULL( "+ endDate+",Tb_Fare.UseDate))";
            }
            
            else
            {
                 sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                            "FROM Tb_Fare" +
                            " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                            +" where " +
                            "Tb_Fare.UseDate BETWEEN (ISNULL(\'"+startDate+" \',Tb_Fare.UseDate)) " +
                                    "  AND (ISNULL( \'"+ endDate+"\',Tb_Fare.UseDate))";
            }
            
        }
        
        else if(info.getUseDate() == null && info.getEndDate() ==null)
        {
            startDate = null;
            endDate = null;
            sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                    "FROM Tb_Fare" +
                    " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                    +" where (Tb_Fare.PersonID =IIF("+info.getPersonID()+ " IS NULL, Tb_Fare.PersonID, "+ info.getPersonID()+ ")) or " +
                    "Tb_Fare.UseDate BETWEEN (ISNULL("+startDate+" ,Tb_Fare.UseDate)) " +
                            " AND (ISNULL( "+ endDate+",Tb_Fare.UseDate))";
            
        }
        
        else if (startDate.equals("") && endDate.equals(""))
        {
        	startDate = null;
            endDate = null;
            sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                    "FROM Tb_Fare" +
                    " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                    +" where (Tb_Fare.PersonID =IIF("+info.getPersonID()+ " IS NULL, Tb_Fare.PersonID, "+ info.getPersonID()+ "))  " ;
                    //+"Tb_Fare.UseDate BETWEEN (ISNULL("+startDate+" ,Tb_Fare.UseDate)) " +
                        //    " AND (ISNULL( "+ endDate+",Tb_Fare.UseDate))";
            
        }
        
        else if(info.getUseDate() != null)
        {
            if( info.getEndDate() == "" && info.getUseDate() != "" ) {
                    
                    endDate="9999-01-01";
                }
                else if( info.getEndDate() == "" && info.getUseDate() != "" ) {
    
                    startDate="1700-01-01";
                }
             sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                        "FROM Tb_Fare" +
                        " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                        +" where (Tb_Fare.PersonID =IIF("+info.getPersonID()+ " IS NULL, Tb_Fare.PersonID, "+ info.getPersonID()+ ")) and " +
                        "Tb_Fare.UseDate BETWEEN (ISNULL(\'"+startDate+" \',Tb_Fare.UseDate)) " +
                                "  AND (ISNULL( \'"+ endDate+"\',Tb_Fare.UseDate))";
        }
        
        
        
             /*
             System.out.println(info.getEndDate());
             if(info.getEndDate() == null)
             {
                 System.out.println(" is empty");
             }
            if( info.getEndDate() != ""  && endDate.equals("")) {//defaultDate.equals("") ||
                
                endDate="9999-01-01";
            }
            if( info.getUseDate() != null && startDate.equals(""))
            {
                startDate = "1900-01-01";
            }
        
        
        sql ="SELECT Tb_Fare.PersonID,Tb_Fare.UseDate,Tb_Fare.Arrival_station,Tb_Fare.Departure_station,Tb_Fare.Purpose,Tb_Fare.FareID,Tb_Fare.Fare,Tb_DutyPerson.PersonName\r\n" +
                "FROM Tb_Fare" +
                " INNER JOIN Tb_DutyPerson ON Tb_Fare.PersonID = Tb_DutyPerson.PersonID"
                +" where (Tb_Fare.PersonID =IIF("+info.getPersonID()+ " IS NULL, Tb_Fare.PersonID, "+ info.getPersonID()+ ")) or " +
                "Tb_Fare.UseDate BETWEEN ISNULL("+startDate+",Tb_Fare.UseDate) AND ISNULL("+endDate+",Tb_Fare.UseDate)";
        /*
             sql ="Select UseDate,Departure_station,Arrival_station,Purpose,Fare,FareID, " +
                        "SUBSTRING((SELECT ',' + PersonName  FROM Tb_DutyPerson" +
                        " where Tb_DutyPerson.PersonID=Tb_Fare.PersonID FOR XML PATH('')), 2, 999999) as PersonName" +
                        " from Tb_Fare" +
                        " where (PersonID =IIF("+info.getPersonID()+ " IS NULL, PersonID, "+ info.getPersonID()+ ")) and "
                        + "(UseDate between \'"+info.getUseDate()+"\' AND \'"+defaultDate+"\' ) ";
                */
        
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        System.out.println(list);
        return list;
    }
    
    public List<Map<String, Object>> getFareInfo()
    {
        String sql ="Select * from Tb_Fare";
          List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
          return list;
    }
}