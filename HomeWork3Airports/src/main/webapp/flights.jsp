<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, java.sql.*, by.it_academy.jd2.core.*, by.it_academy.jd2.core.dto.*" %>

<html>
 <head>
   <title>Airports</title>
   <meta charset="utf-8">
 </head>
 <body>

    <table border="1">
           <caption><h2>Flights</h2></caption>
           <tr>
            <th>Flight No</th>
            <th>Aircraft code</th>
            <th>Aircraft model</th>
            <th>Departure city</th>
            <th>Departure Airport name</th>
            <th>Arrival city</th>
            <th>Arrival Airport name</th>
            <th>Departure date</th>
            <th>Arrival date</th>
           </tr>
    <% int results = PaginationHelper.checkCountOfResults(request.getParameter("dateFrom"), request.getParameter("dateTo"),
                                           request.getParameter("airpDeparture"), request.getParameter("airpArrival"));
    int pages = PaginationHelper.checkCountOfPages(results, 25); %>

    <% Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "postgres");
            String sqlRequest = "SELECT flight_no, departure_airport_name, departure_city, arrival_airport_name, arrival_city, "
                + "flights_v.aircraft_code, bookings.aircrafts.model, actual_departure, actual_arrival FROM bookings.flights_v JOIN "
                + "bookings.aircrafts ON bookings.aircrafts.aircraft_code = bookings.flights_v.aircraft_code WHERE actual_departure >= ? "
                + "AND actual_departure <= ? AND departure_airport_name = ? AND arrival_airport_name = ? ORDER BY actual_departure "
                + "LIMIT 25 OFFSET ?";
            try(PreparedStatement ps = conn.prepareStatement(sqlRequest)) {
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(request.getParameter("dateFrom") + " 00:00:00"));
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(request.getParameter("dateTo") + " 00:00:00"));
                ps.setString(3, request.getParameter("airpDeparture"));
                ps.setString(4, request.getParameter("airpArrival"));
                ps.setLong(5, 25 * Long.parseLong(request.getParameter("page")));
                ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                     %>

                           <tr>
                               <td><%=rs.getString("flight_no")%></td>
                               <td><%=rs.getString("aircraft_code")%></td>
                               <td><%=rs.getString("model")%></td>
                               <td><%=rs.getString("departure_city")%></td>
                               <td><%=rs.getString("departure_airport_name")%></td>
                               <td><%=rs.getString("arrival_city")%></td>
                               <td><%=rs.getString("arrival_airport_name")%></td>
                               <td><%=rs.getString("actual_departure")%></td>
                               <td><%=rs.getString("actual_arrival")%></td>
                           </tr>

                 <% } %>
    </table>
         <% }
            conn.close();%>

            <br>

    <form action="index" method="GET">
            <input type="submit" value="Back">
            </form>
    <br>
    <br>
    <% for(int i = 1; i <= pages; i++) { %>
    <a href="flights?page=<%=i-1%>&airpDeparture=<%=request.getParameter("airpDeparture")%>&airpArrival=<%=request.getParameter("airpArrival")%>&dateTo=<%=request.getParameter("dateTo")%>&dateFrom=<%=request.getParameter("dateFrom")%>"><%=i%></a>
    <% } %>
    <br>
    <br>

 </body>
</html>