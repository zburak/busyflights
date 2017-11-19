package com.travix.medusa.busyflights.converter.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component("toughJetResponseConverter")
public class ToughJetResponseConverter implements Converter<ToughJetResponse, BusyFlightsResponse> {

  @Override
  public BusyFlightsResponse convert(final ToughJetResponse source) {

    log.trace("ToughJet response conversion started");

    final String returnDate = source.getInboundDateTime();
    final String departureDate = source.getOutboundDateTime();

    final DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
    final String formattedDepartureDate =
        LocalDateTime.ofInstant(Instant.parse(returnDate), ZoneOffset.UTC).format(isoDateTime);
    final String formattedReturnDate =
        LocalDateTime.ofInstant(Instant.parse(departureDate), ZoneOffset.UTC).format(isoDateTime);

    final BusyFlightsResponse response =
        BusyFlightsResponse.builder()
            .airline(source.getCarrier())
            .departureAirportCode(source.getOutboundDateTime())
            .destinationAirportCode(source.getInboundDateTime())
            .fare(
                BigDecimal.valueOf(calculateFare(source))
                    .setScale(2, RoundingMode.HALF_UP)
                    .toPlainString())
            .supplier("ToughJet")
            .arrivalDate(formattedReturnDate)
            .departureDate(formattedDepartureDate)
            .build();

    log.trace("Converted response is : ", response);
    return response;
  }

  private double calculateFare(final ToughJetResponse source) {
    final double discount = source.getDiscount();
    final double tax = source.getTax();
    final double basePrice = source.getBasePrice();

    final double discountedPrice = basePrice * ((100D - discount) / 100);
    final double finalPrice = discountedPrice * ((100D + tax) / 100);

    log.trace(
        "tax: {} discount: {} price: {} finalPrice: {}", tax, discount, basePrice, finalPrice);

    return finalPrice;
  }
}
