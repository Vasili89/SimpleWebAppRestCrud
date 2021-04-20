package by.it_academy.jd2.core;

import java.sql.*;

public class PaginationHelper {

    public static int checkCountOfResults(String dateFrom, String dateTo, String airpDeparture, String airpArrival) {
        int count = 0;
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
        return count;
    }

    public static int checkCountOfPages(int results, int resultsOnPage) {
        if (results % resultsOnPage == 0) return results/resultsOnPage;
        else return results/resultsOnPage + 1;
    }

}
