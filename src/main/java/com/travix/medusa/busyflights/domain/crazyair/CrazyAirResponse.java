package com.travix.medusa.busyflights.domain.crazyair;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CrazyAirResponse implements Serializable {

  private String airline;
  private double price;
  private String cabinclass;
  private String departureAirportCode;
  private String destinationAirportCode;
  private String departureDate;
  private String arrivalDate;

  public String getAirline() {
    return airline;
  }

  public void setAirline(final String airline) {
    this.airline = airline;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(final double price) {
    this.price = price;
  }

  public String getCabinclass() {
    return cabinclass;
  }

  public void setCabinclass(final String cabinclass) {
    this.cabinclass = cabinclass;
  }

  public String getDepartureAirportCode() {
    return departureAirportCode;
  }

  public void setDepartureAirportCode(final String departureAirportCode) {
    this.departureAirportCode = departureAirportCode;
  }

  public String getDestinationAirportCode() {
    return destinationAirportCode;
  }

  public void setDestinationAirportCode(final String destinationAirportCode) {
    this.destinationAirportCode = destinationAirportCode;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(final String departureDate) {
    this.departureDate = departureDate;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(final String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }
}
