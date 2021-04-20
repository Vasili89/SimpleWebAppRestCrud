package by.it_academy.jd2.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet(name = "Aircheck", urlPatterns = "/aircheck")
public class Aircheck extends HttpServlet {

    public String airpDeparture;
    public String departureDate;
    public String airpArrival;
    public String arrivalDate;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        airpDeparture = req.getParameter("airpDeparture");
        departureDate = req.getParameter("departureDate");
        airpArrival = req.getParameter("airpArrival");
        arrivalDate = req.getParameter("arrivalDate");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<p>" + departureDate + "</p>");
        writer.write("<p>" + arrivalDate + "</p>");


    }

    public static Calendar parseStringToCalendar(String s) {
        int year = Integer.parseInt(s.substring(0,4));
        int month = Integer.parseInt(s.substring(5,7)) - 1;
        int day = Integer.parseInt(s.substring(8));
        return new GregorianCalendar(year, month, day);
    }
}
