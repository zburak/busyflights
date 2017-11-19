package com.travix.medusa.busyflights.supplier.client;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("toughJetClient")
public class ToughJetClient extends AbstractSupplierClient<ToughJetRequest, ToughJetResponse> {

  @Value("${supplier.toughjet.endpoint}")
  private String endpoint;

  @Override
  protected Class<ToughJetResponse[]> getResponseClass() {
    return ToughJetResponse[].class;
  }

  @Override
  String getEndpoint() {
    return endpoint;
  }
}
