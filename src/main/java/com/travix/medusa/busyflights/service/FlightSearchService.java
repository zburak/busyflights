package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import java.util.List;

public interface FlightSearchService {

  /**
   * Process the request and aggregates results from suppliers
   *
   * @param req
   * @return
   */
  List<BusyFlightsResponse> searchFlights(BusyFlightsRequest req);

}
