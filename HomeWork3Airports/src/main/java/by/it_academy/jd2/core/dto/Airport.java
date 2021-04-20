package by.it_academy.jd2.core.dto;

public class Airport {

    private String airpName;
    private String airpCity;
    private String airpCode;
    private String airpTime;
    private String airpCoord;

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
