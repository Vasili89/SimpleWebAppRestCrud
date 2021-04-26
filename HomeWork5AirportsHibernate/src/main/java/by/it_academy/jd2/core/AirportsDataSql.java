package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.Airport;
import by.it_academy.jd2.dao.AirportsListDao;

import java.sql.*;
import java.util.*;

public class AirportsDataSql implements AirportsListDao {

    public Map<String, Airport> createAirportsList() throws SQLException, ClassNotFoundException {
        Map<String, Airport> air = new HashMap<>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "postgres");
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookings.airports");) {
            while (rs.next()) {
                String city = rs.getString("city");
                String code = rs.getString("airport_code");
                String time = rs.getString("timezone");
                String coord = rs.getString("coordinates");
                air.put(rs.getString("airport_name"), new Airport(city, code, time, coord));
            }
        }
        return air;
    }

    public ArrayList<String> createAirportsNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> airNames = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "postgres");
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT airport_name FROM bookings.airports ORDER BY airport_name");) {
            while (rs.next()) {
                airNames.add(rs.getString("airport_name"));
            }
        }
        return airNames;
    }
}
