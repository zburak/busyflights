package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BusyFlightsResponse {

  private String airline;
  private String supplier;
  private String fare;
  private String departureAirportCode;
  private String destinationAirportCode;
  private String departureDate;
  private String arrivalDate;
}
