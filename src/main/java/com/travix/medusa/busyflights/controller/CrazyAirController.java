package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Endpoint for mock response generation */
@RestController
@RequestMapping(value = "/crazyAir")
public class CrazyAirController {

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ResponseEntity<List<CrazyAirResponse>> search(@Valid CrazyAirRequest request) {

    final CrazyAirResponse resp1 =
        CrazyAirResponse.builder()
            .airline("LH")
            .cabinclass("E")
            .departureAirportCode(request.getOrigin())
            .destinationAirportCode(request.getDestination())
            .price(112D)
            .departureDate(
                LocalDate.parse(request.getDepartureDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atTime(12, 15)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .arrivalDate(
                LocalDate.parse(request.getReturnDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atTime(21, 15)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();

    final CrazyAirResponse resp2 =
        CrazyAirResponse.builder()
            .airline("BA")
            .cabinclass("B")
            .departureAirportCode(request.getOrigin())
            .destinationAirportCode(request.getDestination())
            .price(305D)
            .departureDate(
                LocalDate.parse(request.getDepartureDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atTime(14, 20)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .arrivalDate(
                LocalDate.parse(request.getReturnDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atTime(22, 30)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();

    return new ResponseEntity<>(Arrays.asList(resp1, resp2), HttpStatus.OK);
  }
}
