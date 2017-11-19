package com.travix.medusa.busyflights.config;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.GenericSupplierSearchStrategy;
import com.travix.medusa.busyflights.supplier.SupplierSearchStrategy;
import com.travix.medusa.busyflights.supplier.client.AbstractSupplierClient;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BusyFlightsConfig {

  @Resource(name = "crazyAirClient")
  private AbstractSupplierClient<CrazyAirRequest, CrazyAirResponse> crazyAirClient;

  @Resource(name = "crazyAirRequestConverter")
  private Converter<BusyFlightsRequest, CrazyAirRequest> crazyAirRequestConverter;

  @Resource(name = "crazyAirResponseConverter")
  private Converter<CrazyAirResponse, BusyFlightsResponse> crazyAirResponseConverter;

  @Resource(name = "toughJetRequestConverter")
  private Converter<BusyFlightsRequest, ToughJetRequest> toughJetRequestConverter;

  @Resource(name = "toughJetResponseConverter")
  private Converter<ToughJetResponse, BusyFlightsResponse> toughJetResponseConverter;

  @Resource(name = "toughJetClient")
  private AbstractSupplierClient<ToughJetRequest, ToughJetResponse> toughJetClient;

  @Bean(name = "crazyAirSearchStrategy")
  public SupplierSearchStrategy crazyAirSearchStrategy() {
    GenericSupplierSearchStrategy<CrazyAirRequest, CrazyAirResponse> searchStrategy =
        new GenericSupplierSearchStrategy<>();
    searchStrategy.setClient(crazyAirClient);
    searchStrategy.setRequestConverter(crazyAirRequestConverter);
    searchStrategy.setResponseConverter(crazyAirResponseConverter);
    return searchStrategy;
  }

  @Bean(name = "toughJetSearchStrategy")
  public SupplierSearchStrategy toughJetSearchStrategy() {
    GenericSupplierSearchStrategy<ToughJetRequest, ToughJetResponse> searchStrategy =
        new GenericSupplierSearchStrategy<>();
    searchStrategy.setClient(toughJetClient);
    searchStrategy.setRequestConverter(toughJetRequestConverter);
    searchStrategy.setResponseConverter(toughJetResponseConverter);
    return searchStrategy;
  }

  @Bean(name = "supplierRestTemplate")
  public RestTemplate supplierRestTemplate() {
    return new RestTemplate();
  }
}
