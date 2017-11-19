package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.client.AbstractSupplierClient;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;

public class GenericSupplierSearchStrategy<REQ, RESP> implements SupplierSearchStrategy {

  @Setter
  private Converter<BusyFlightsRequest, REQ> requestConverter;

  @Setter
  private Converter<RESP, BusyFlightsResponse> responseConverter;

  @Setter
  private AbstractSupplierClient<REQ, RESP> client;

  @Override
  public List<BusyFlightsResponse> search(final BusyFlightsRequest request) {

    final REQ externalRequest = requestConverter.convert(request);
    final List<RESP> externalResponses = client.get(externalRequest);

    return externalResponses.stream().map(responseConverter::convert).collect(Collectors.toList());
  }
}
