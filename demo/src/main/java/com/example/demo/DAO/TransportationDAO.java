package com.example.demo.DAO;

import java.awt.print.Book;
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
	
	/*public List<FareInfo> selectBookList() {
        return jdbcTemplate.query("SELECT * FROM book", new RowMapper<FareInfo>() {
            public FareInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            	FareInfo fareInfo = new FareInfo();
            	fareInfo.setFareID(rs.getInt(1));
            	fareInfo.setPersonID(rs.getInt(2));
            	fareInfo.setUseDate(rs.getString(3));
            	fareInfo.setDepartureStation(rs.getString(4));
                return book;
                
            	private int FareID;
            	private int PersonID;
            	private String UseDate;
            	private String Departure_Station;
            	private String Arrival_Station;
            	private String Purpose;
            	private int Fare;
            }
        });
    }*/
}
