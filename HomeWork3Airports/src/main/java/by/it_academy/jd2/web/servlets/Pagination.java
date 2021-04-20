package by.it_academy.jd2.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Pagination", urlPatterns = "/pagination")
public class Pagination extends HttpServlet {

    int count;
    int pages;
    public String airpDeparture;
    public String dateFrom;
    public String airpArrival;
    public String dateTo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        airpDeparture = req.getParameter("airpDeparture");
        airpArrival = req.getParameter("airpArrival");
        dateFrom = req.getParameter("dateFrom");
        dateTo = req.getParameter("dateTo");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "postgres");
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM bookings.flights_v WHERE actual_departure>=? AND actual_departure<=? AND departure_airport_name = ? AND arrival_airport_name = ?");) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(dateFrom + " 00:00:00"));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(dateTo + " 00:00:00"));
            ps.setString(3, airpDeparture);
            ps.setString(4, airpArrival);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = (int) rs.getLong("count");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
