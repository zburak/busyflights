package com.travix.medusa.busyflights.supplier;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.supplier.client.AbstractSupplierClient;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

public class GenericSupplierSearchStrategyTest {

  @Mock
  Converter<BusyFlightsRequest, CrazyAirRequest> crazyAirRequestConverter;

  @Mock
  Converter<CrazyAirResponse, BusyFlightsResponse> crazyAirResponseConverter;

  @Mock
  AbstractSupplierClient<CrazyAirRequest, CrazyAirResponse> crazyAirClient;

  @Mock
  Converter<BusyFlightsRequest, ToughJetRequest> toughJetRequestConverter;

  @Mock
  Converter<ToughJetResponse, BusyFlightsResponse> toughJetResponseConverter;

  @Mock
  AbstractSupplierClient<ToughJetRequest, ToughJetResponse> toughJetClient;

  @InjectMocks
  GenericSupplierSearchStrategy genericSupplierSearchStrategy;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSearchForCrazyAir() throws Exception {

    genericSupplierSearchStrategy.setResponseConverter(crazyAirResponseConverter);
    genericSupplierSearchStrategy.setRequestConverter(crazyAirRequestConverter);
    genericSupplierSearchStrategy.setClient(crazyAirClient);

    CrazyAirRequest mockCrazyAirRequest = Mockito.mock(CrazyAirRequest.class);
    CrazyAirResponse mockCrazyAirResponse1 = Mockito.mock(CrazyAirResponse.class);
    CrazyAirResponse mockCrazyAirResponse2 = Mockito.mock(CrazyAirResponse.class);
    BusyFlightsResponse actualResp1 = Mockito.mock(BusyFlightsResponse.class);
    BusyFlightsResponse actualResp2 = Mockito.mock(BusyFlightsResponse.class);

    final List<CrazyAirResponse> crazyAirResponses =
        Arrays.asList(mockCrazyAirResponse1, mockCrazyAirResponse2);

    final List<BusyFlightsResponse> actualResponses = Arrays.asList(actualResp1, actualResp2);

    doReturn(mockCrazyAirRequest)
        .when(crazyAirRequestConverter)
        .convert(any(BusyFlightsRequest.class));
    doReturn(crazyAirResponses).when(crazyAirClient).get(mockCrazyAirRequest);
    doReturn(actualResp1).when(crazyAirResponseConverter).convert(mockCrazyAirResponse1);
    doReturn(actualResp2).when(crazyAirResponseConverter).convert(mockCrazyAirResponse2);

    List<BusyFlightsResponse> result =
        genericSupplierSearchStrategy.search(new BusyFlightsRequest());

    verify(crazyAirResponseConverter, times(actualResponses.size())).convert(any());
    Assert.assertEquals(actualResponses, result);
  }

  @Test
  public void testSearchForToughJet() throws Exception {

    genericSupplierSearchStrategy.setResponseConverter(toughJetResponseConverter);
    genericSupplierSearchStrategy.setRequestConverter(toughJetRequestConverter);
    genericSupplierSearchStrategy.setClient(toughJetClient);

    ToughJetRequest mockToughJetRequest = Mockito.mock(ToughJetRequest.class);
    ToughJetResponse mockToughJetResponse1 = Mockito.mock(ToughJetResponse.class);
    ToughJetResponse mockToughJetResponse2 = Mockito.mock(ToughJetResponse.class);
    BusyFlightsResponse actualResp1 = Mockito.mock(BusyFlightsResponse.class);
    BusyFlightsResponse actualResp2 = Mockito.mock(BusyFlightsResponse.class);

    final List<ToughJetResponse> toughJetResponses =
        Arrays.asList(mockToughJetResponse1, mockToughJetResponse2);

    final List<BusyFlightsResponse> actualResponses = Arrays.asList(actualResp1, actualResp2);

    doReturn(mockToughJetRequest)
        .when(toughJetRequestConverter)
        .convert(any(BusyFlightsRequest.class));
    doReturn(toughJetResponses).when(toughJetClient).get(mockToughJetRequest);
    doReturn(actualResp1).when(toughJetResponseConverter).convert(mockToughJetResponse1);
    doReturn(actualResp2).when(toughJetResponseConverter).convert(mockToughJetResponse2);

    List<BusyFlightsResponse> result =
        genericSupplierSearchStrategy.search(new BusyFlightsRequest());

    verify(toughJetResponseConverter, times(actualResponses.size())).convert(any());
    Assert.assertEquals(actualResponses, result);
  }
}
