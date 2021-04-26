package by.it_academy.jd2.core.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "airports")
@Table(name = "airports")
public class Airport implements Serializable {

    @Column(name = "airport_name")
    private String airpName;

    @Column(name = "city")
    private String airpCity;

    @Id
    @Column(name = "airport_code")
    private String airpCode;

    @Column(name = "timezone")
    private String airpTime;

    @Column(name = "coordinates")
    private String airpCoord;

    public Airport() {
    }

    public void setAirpName(String airpName) {
        this.airpName = airpName;
    }

    public void setAirpCity(String airpCity) {
        this.airpCity = airpCity;
    }

    public void setAirpCode(String airpCode) {
        this.airpCode = airpCode;
    }

    public void setAirpTime(String airpTime) {
        this.airpTime = airpTime;
    }

    public void setAirpCoord(String airpCoord) {
        this.airpCoord = airpCoord;
    }

    public String getAirpName() {
        return airpName;
    }

    public String getAirpCity() {
        return airpCity;
    }

    public String getAirpCode() {
        return airpCode;
    }

    public String getAirpTime() {
        return airpTime;
    }

    public String getAirpCoord() {
        return airpCoord;
    }

    public Airport(String airpCity, String airpCode, String airpTime, String airpCoord) {
        this.airpCity = airpCity;
        this.airpCode = airpCode;
        this.airpTime = airpTime;
        this.airpCoord = airpCoord;
    }
}
