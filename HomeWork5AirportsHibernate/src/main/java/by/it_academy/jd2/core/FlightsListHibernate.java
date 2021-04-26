package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.Airport;
import by.it_academy.jd2.core.dto.Flight;
import by.it_academy.jd2.dao.FlightsListDao;
import by.it_academy.jd2.dao.HibernateCreator;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightsListHibernate implements FlightsListDao {

    private Session session = HibernateCreator.getInstance().openSession();
    private ArrayList<Flight> myFlightsList;

    @Override
    public List<Flight> getFlightsList(HttpServletRequest request) throws ClassNotFoundException, SQLException {

        String myFlights = "FROM flights WHERE actual_departure >= :param1 " +
                "AND actual_departure <= :param2 AND departure_airport = :param3 AND " +
                "arrival_airport = :param4 ORDER BY actual_departure";
        Query<Flight> query = this.session.createQuery(myFlights);
        query.setParameter("param1", java.sql.Timestamp.valueOf(request.getParameter("dateFrom") + " 00:00:00"));
        query.setParameter("param2", java.sql.Timestamp.valueOf(request.getParameter("dateTo") + " 23:59:59"));
        Airport airportDep = (Airport) this.session.createQuery("FROM airports WHERE airport_name = '"
                + request.getParameter("airpDeparture") + "'").uniqueResult();
        query.setParameter("param3", airportDep.getAirpCode());
        Airport airportArr = (Airport) this.session.createQuery("FROM airports WHERE airport_name = '"
                + request.getParameter("airpArrival") + "'").uniqueResult();
        query.setParameter("param4", airportArr.getAirpCode());
        query.setMaxResults(25);
        query.setFirstResult(Integer.parseInt(request.getParameter("page")));
        List<Flight> flights = query.list();
        this.myFlightsList = new ArrayList<>();
        this.myFlightsList.addAll(flights);
        return this.myFlightsList;
    }
}
