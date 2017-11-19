package com.travix.medusa.busyflights.converter.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component("crazyAirRequestConverter")
public class CrazyAirRequestConverter implements Converter<BusyFlightsRequest, CrazyAirRequest> {

  @Override
  public CrazyAirRequest convert(final BusyFlightsRequest source) {

    log.trace("CrazyAir request conversion started");

    final CrazyAirRequest request =
        CrazyAirRequest.builder()
            .destination(source.getDestination())
            .origin(source.getOrigin())
            .passengerCount(source.getNumberOfPassengers())
            .departureDate(source.getDepartureDate())
            .returnDate(source.getReturnDate())
            .build();

    log.trace("Converted request is : ", request);

    return request;
  }
}
