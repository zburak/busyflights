package com.travix.medusa.busyflights.converter.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component("toughJetRequestConverter")
public class ToughJetRequestConverter implements Converter<BusyFlightsRequest, ToughJetRequest> {

  @Override
  public ToughJetRequest convert(final BusyFlightsRequest source) {
    log.trace("ToughJet conversion started");

    final ToughJetRequest request =
        ToughJetRequest.builder()
            .from(source.getOrigin())
            .to(source.getDestination())
            .numberOfAdults(source.getNumberOfPassengers())
            .inboundDate(source.getReturnDate())
            .outboundDate(source.getDepartureDate())
            .build();

    log.trace("Converted request is : ", request);
    return request;
  }
}
