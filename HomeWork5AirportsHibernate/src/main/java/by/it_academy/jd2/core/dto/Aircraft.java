package by.it_academy.jd2.core.dto;

import javax.persistence.*;

@Entity(name = "aircrafts")
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @Column(name = "aircraft_code")
    private String aircraftCode;

    @Column(name = "model")
    private String model;

    @Column(name = "range")
    private Integer range;

    public Aircraft() {
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
