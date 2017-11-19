package com.travix.medusa.busyflights.supplier.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("crazyAirClient")
public class CrazyAirClient extends AbstractSupplierClient<CrazyAirRequest, CrazyAirResponse> {

  @Value("${supplier.crazyair.endpoint}")
  private String endpoint;

  @Override
  protected Class<CrazyAirResponse[]> getResponseClass() {
    return CrazyAirResponse[].class;
  }

  @Override
  String getEndpoint() {
    return endpoint;
  }
}
