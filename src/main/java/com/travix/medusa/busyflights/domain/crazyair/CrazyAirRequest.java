package com.travix.medusa.busyflights.domain.crazyair;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CrazyAirRequest implements Serializable {

  private String origin;
  private String destination;
  private String departureDate;
  private String returnDate;
  private int passengerCount;

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(final String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(final String destination) {
    this.destination = destination;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(final String departureDate) {
    this.departureDate = departureDate;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(final String returnDate) {
    this.returnDate = returnDate;
  }

  public int getPassengerCount() {
    return passengerCount;
  }

  public void setPassengerCount(final int passengerCount) {
    this.passengerCount = passengerCount;
  }
}
