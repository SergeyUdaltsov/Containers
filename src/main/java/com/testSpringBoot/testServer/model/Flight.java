package com.testSpringBoot.testServer.model;

/**
 * @author Serhii_Udaltsov on 10/9/2020
 */
public class Flight {
    private String name;
    private long departure;
    private long arrivale;
    private String departureCity;
    private String arrivalCity;

    public Flight(String name, long departure, long arrivale, String departureCity, String arrivalCity) {
        this.name = name;
        this.departure = departure;
        this.arrivale = arrivale;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDeparture() {
        return departure;
    }

    public void setDeparture(long departure) {
        this.departure = departure;
    }

    public long getArrivale() {
        return arrivale;
    }

    public void setArrivale(long arrivale) {
        this.arrivale = arrivale;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
