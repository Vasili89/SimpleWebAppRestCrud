<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, java.sql.*, by.it_academy.jd2.core.*, by.it_academy.jd2.core.dto.*" %>

<html>
 <head>
   <title>Airports</title>
   <meta charset="utf-8">
 </head>
 <body>
 <% AirportsData airportsData = new AirportsData(); %>
 <% ArrayList<String> airportsList = airportsData.getAirportsNames(); %>
 <% Collections.sort(airportsList); %>

    <h1>Find Flight Information</h1>
    <h3>Input departure airport</h3>
    <form action="flights" method="GET">
    <p><select name="airpDeparture">
        <% for (int i = 0; i < airportsList.size(); i++) { %>

        <option value=<%=airportsList.get(i)%>><%=airportsList.get(i)%></option>
        <%}%>
       </select>
        Date of departure from: <input type="date" name="dateFrom" />

        <br>
        <h3>Input arrival airport</h3>

    <p><select name="airpArrival">
            <% for (int i = 0; i < airportsList.size(); i++) { %>

            <option value=<%=airportsList.get(i)%>><%=airportsList.get(i)%></option>
            <%}%>
           </select>
            Date of departure from to: <input type="date" name="dateTo" />

            <input type="hidden" name="page" value="0" />

            <br>
            <br>

        <input type="submit" value="FIND" />
        </form>

        <p><a href="airports.jsp">Russian Airports List</a></p>

    <br>

    <br>
    <img height="300" width="300" src="https://www.it-academy.by/local/images/logo.svg">
 </body>
</html>