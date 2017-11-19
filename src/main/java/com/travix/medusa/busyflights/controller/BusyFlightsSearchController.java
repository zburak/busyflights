package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.FlightSearchService;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/busyFlights")
public class BusyFlightsSearchController {

  @Resource private FlightSearchService flightSearchService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<String> hello() {

    String message = "BusyFlight Search API";

    return new ResponseEntity<>(message, HttpStatus.OK);
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ResponseEntity<List<BusyFlightsResponse>> search(@Valid BusyFlightsRequest request) {

    final List<BusyFlightsResponse> busyFlightsResponses;
    try {

      busyFlightsResponses = flightSearchService.searchFlights(request);

      return new ResponseEntity<>(busyFlightsResponses, HttpStatus.OK);

    } catch (Exception e) {
      log.error("Request can not be handled properly", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
