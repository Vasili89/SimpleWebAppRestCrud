<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, by.it_academy.jd2.dao.*, java.text.*, java.sql.*, by.it_academy.jd2.core.*, by.it_academy.jd2.core.dto.*" %>

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

    <% FlightsListDao myFlights = new FlightsListHibernate();
        List<Flight> myFlightsList = myFlights.getFlightsList(request);
        for (int i = 0; i < myFlightsList.size(); i++) {%>

                       <tr>
                           <td><%=myFlightsList.get(i).getFlightNo()%></td>
                           <td><%=myFlightsList.get(i).getAircraft().getAircraftCode()%></td>
                           <td><%=myFlightsList.get(i).getAircraft().getModel()%></td>
                           <td><%=myFlightsList.get(i).getDepartureAirport().getAirpCity()%></td>
                           <td><%=myFlightsList.get(i).getDepartureAirport().getAirpName()%></td>
                           <td><%=myFlightsList.get(i).getArrivalAirport().getAirpCity()%></td>
                           <td><%=myFlightsList.get(i).getArrivalAirport().getAirpName()%></td>
                           <td><%=myFlightsList.get(i).getActualDeparture()%></td>
                           <td><%=myFlightsList.get(i).getActualArrival()%></td>
                       </tr>

                 <% } %>
    </table>

            <br>

    <form action="index" method="GET">
            <input type="submit" value="New find">
            </form>
    <br>
    <br>
    <% String prev = "prev";
       String next = "next";
       int results = Integer.parseInt(request.getParameter("page"));
       int nextPage = results + 25;
       int prevPage = results - 25;
       if (results >= 25) { %>
    <a href="flights?page=<%=prevPage%>&airpDeparture=<%=request.getParameter("airpDeparture")%>&airpArrival=<%=request.getParameter("airpArrival")%>&dateTo=<%=request.getParameter("dateTo")%>&dateFrom=<%=request.getParameter("dateFrom")%>"><%=prev%></a>
          <%  if (myFlightsList.size() == 25) { %>
    <a href="flights?page=<%=nextPage%>&airpDeparture=<%=request.getParameter("airpDeparture")%>&airpArrival=<%=request.getParameter("airpArrival")%>&dateTo=<%=request.getParameter("dateTo")%>&dateFrom=<%=request.getParameter("dateFrom")%>"><%=next%></a>
        <% } } else if (myFlightsList.size() == 25) { %>
    <a href="flights?page=<%=nextPage%>&airpDeparture=<%=request.getParameter("airpDeparture")%>&airpArrival=<%=request.getParameter("airpArrival")%>&dateTo=<%=request.getParameter("dateTo")%>&dateFrom=<%=request.getParameter("dateFrom")%>"><%=next%></a>
        <% } %>

    <br>
    <br>

 </body>
</html>