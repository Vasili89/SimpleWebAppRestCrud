package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.Airport;
import by.it_academy.jd2.dao.AirportsListDao;
import by.it_academy.jd2.dao.HibernateCreator;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.*;

public class AirportsDataHibernate implements AirportsListDao {

    Session session = HibernateCreator.getInstance().openSession();
    Query<Airport> query = session.createQuery("FROM airports");
    List<Airport> airports = query.list();

    @Override
    public Map<String, Airport> createAirportsList() throws SQLException, ClassNotFoundException {
        Map<String, Airport> airportMap = new HashMap<>();
        for (Airport airport : airports) {
            airportMap.put(airport.getAirpName(), airport);
        }
        return airportMap;
    }

    @Override
    public List<String> createAirportsNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> airportsNames = new ArrayList<>();
        for (Airport airport : airports) {
            airportsNames.add(airport.getAirpName());
        }
        Collections.sort(airportsNames);
        return airportsNames;
    }
}
