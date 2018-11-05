package com.example.demo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FareInfo;

@Repository
public class TransportationDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<FareInfo> selectBookList() {
        return jdbcTemplate.query("SELECT * FROM Tb_DutyPerson", new RowMapper<FareInfo>() {
            public FareInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            	FareInfo fareInfo = new FareInfo();
            	
            	fareInfo.setFareID(rs.getInt(1));
            	fareInfo.setPersonID(rs.getInt(2));
            	fareInfo.setUseDate(rs.getString(3));
            	fareInfo.setDepartureStation(rs.getString(4));
            	fareInfo.setArrivalStation(rs.getString(5));;
            	fareInfo.setPurpose(rs.getString(6));
            	fareInfo.setFare(rs.getInt(7));
                return fareInfo;
            }
        });
    }
    
	public void insert (FareInfo fareInfo) {
	     jdbcTemplate.update ("INSERT INTO Tb_Fare (PersonID, UseDate, Departure_station, Arrival_station, Purpose, Fare) VALUES (?,?,?,?,?,?)" ,
	             1, fareInfo.getUseDate(), fareInfo.getDepartureStation(), fareInfo.getArrivalStation(), fareInfo.getPurpose(), fareInfo.getFare());
	}

	public void delete (FareInfo fareInfo) {
	     jdbcTemplate.update ( "DELETE FROM book WHERE book_id =?" ,
	    		 fareInfo.getFareID());
	}
	
	/*public  void update (FareInfo fareInfo) {
	     jdbcTemplate.update ( "UPDATE Tb_Fare SET UseDate = ?, Departure_station =? WHERE PersonID =?" ,
	     );
	}*/
}
