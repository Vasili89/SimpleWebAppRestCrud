package by.it_academy.jd2.dao;

import by.it_academy.jd2.core.dto.Airport;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AirportsListDao {

    public Map<String, Airport> createAirportsList() throws SQLException, ClassNotFoundException;
    public List<String> createAirportsNames()throws SQLException, ClassNotFoundException;
}
