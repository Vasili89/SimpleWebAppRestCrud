<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, by.it_academy.jd2.dao.*, java.sql.*, by.it_academy.jd2.core.*, by.it_academy.jd2.core.dto.*" %>

<html>
 <head>
   <title>Airports</title>
   <meta charset="utf-8">
 </head>
 <body>

    <% AirportsListDao airports = new AirportsDataHibernate();
       List<String> arr = airports.createAirportsNames();
       Map<String, Airport> map = airports.createAirportsList(); %>

    <table border="1">
       <caption><h2>Russian Airports</h2></caption>
       <tr>
        <th>Airport name</th>
        <th>City</th>
        <th>Airport code</th>
        <th>Timezone</th>
        <th>Coordinates</th>
       </tr>
       <% for (int i = 0; i < arr.size(); i++) { %>
       <tr>
           <td><%=arr.get(i)%></td>
           <td><%=map.get(arr.get(i)).getAirpCity()%></td>
           <td><%=map.get(arr.get(i)).getAirpCode()%></td>
           <td><%=map.get(arr.get(i)).getAirpTime()%></td>
           <td><%=map.get(arr.get(i)).getAirpCoord()%></td>
       </tr>
       <% } %>
      </table>

    <br>

    <form action="index" method="GET">
            <input type="submit" value="Back">
            </form>
 </body>
</html>