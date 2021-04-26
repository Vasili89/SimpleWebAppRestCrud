package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.Aircraft;
import by.it_academy.jd2.core.dto.Airport;
import by.it_academy.jd2.core.dto.Flight;
import by.it_academy.jd2.dao.FlightsListDao;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.*;

public class FlightsListSql implements FlightsListDao {

    private ArrayList<Flight> myFlightsList;

    public List<Flight> getFlightsList(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        this.myFlightsList = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "postgres");
        String sqlRequest = "SELECT flight_no, departure_airport_name, departure_city, arrival_airport_name, arrival_city, "
                + "flights_v.aircraft_code, bookings.aircrafts.model, actual_departure, actual_arrival FROM bookings.flights_v JOIN "
                + "bookings.aircrafts ON bookings.aircrafts.aircraft_code = bookings.flights_v.aircraft_code WHERE actual_departure >= ? "
                + "AND actual_departure <= ? AND departure_airport_name = ? AND arrival_airport_name = ? ORDER BY actual_departure "
                + "LIMIT 25 OFFSET ?";
        try(PreparedStatement ps = conn.prepareStatement(sqlRequest)) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(request.getParameter("dateFrom") + " 00:00:00"));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(request.getParameter("dateTo") + " 23:59:59"));
            ps.setString(3, request.getParameter("airpDeparture"));
            ps.setString(4, request.getParameter("airpArrival"));
            ps.setLong(5, Long.parseLong(request.getParameter("page")));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Flight currentFlight = new Flight();
                Airport departureAirport = new Airport();
                Airport arrivalAirport = new Airport();
                Aircraft currentAircraft = new Aircraft();
                currentAircraft.setAircraftCode(rs.getString("aircraft_code"));
                currentAircraft.setModel(rs.getString("model"));
                departureAirport.setAirpCity(rs.getString("departure_city"));
                departureAirport.setAirpName(rs.getString("departure_airport_name"));
                arrivalAirport.setAirpCity(rs.getString("arrival_city"));
                arrivalAirport.setAirpName(rs.getString("arrival_airport_name"));
                currentFlight.setFlightNo(rs.getString("flight_no"));
                currentFlight.setAircraft(currentAircraft);
                currentFlight.setActualDeparture(rs.getString("actual_departure"));
                currentFlight.setActualArrival(rs.getString("actual_arrival"));
                currentFlight.setDepartureAirport(departureAirport);
                currentFlight.setArrivalAirport(arrivalAirport);
                this.myFlightsList.add(currentFlight);
            }
        }
        return this.myFlightsList;
    }
}
