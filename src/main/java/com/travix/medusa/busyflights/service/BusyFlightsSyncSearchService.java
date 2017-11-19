package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.SupplierSearchStrategy;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BusyFlightsSyncSearchService implements FlightSearchService {

  @Resource
  private List<SupplierSearchStrategy> supplierSearchStrategies;

  @Override
  public List<BusyFlightsResponse> searchFlights(final BusyFlightsRequest req) {

    final List<BusyFlightsResponse> responseList =
        supplierSearchStrategies
            .stream()
            .map(strategy -> strategy.search(req))
            .flatMap(Collection::stream)
            .sorted(Comparator.comparingDouble(r -> Double.valueOf(r.getFare())))
            .collect(Collectors.toList());

    log.debug("Aggregated responselist size: ", responseList.size());

    return responseList;
  }
}
