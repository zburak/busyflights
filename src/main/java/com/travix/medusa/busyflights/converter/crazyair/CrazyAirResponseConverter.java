package com.travix.medusa.busyflights.converter.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse.BusyFlightsResponseBuilder;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component("crazyAirResponseConverter")
public class CrazyAirResponseConverter implements Converter<CrazyAirResponse, BusyFlightsResponse> {

  @Override
  public BusyFlightsResponse convert(final CrazyAirResponse source) {

    log.trace("CrazyAir response conversion started");

    final String returnDate = source.getArrivalDate();
    final String departureDate = source.getDepartureDate();

    final DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    final DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
    final String formattedDepartureDate = LocalDateTime.parse(departureDate, isoLocalDateTime).format(isoDateTime);
    final String formattedReturnDate = LocalDateTime.parse(returnDate, isoLocalDateTime).format(isoDateTime);

    final BusyFlightsResponse response = BusyFlightsResponse.builder()
        .airline(source.getAirline())
        .departureAirportCode(source.getDepartureAirportCode())
        .destinationAirportCode(source.getDestinationAirportCode())
        .fare(
            BigDecimal.valueOf(source.getPrice()).setScale(2, RoundingMode.HALF_UP).toPlainString())
        .supplier("CrazyAir")
        .arrivalDate(formattedReturnDate)
        .departureDate(formattedDepartureDate).build();

    log.trace("Converted response is : ", response);

    return response;
  }
}
