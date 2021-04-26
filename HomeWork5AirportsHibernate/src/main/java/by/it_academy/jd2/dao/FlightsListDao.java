package by.it_academy.jd2.dao;

import by.it_academy.jd2.core.dto.Flight;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public interface FlightsListDao {

    public List<Flight> getFlightsList(HttpServletRequest request)  throws ClassNotFoundException, SQLException;
}
