package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.supplier.SupplierSearchStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

public class BusyFlightsSyncSearchServiceTest {

  @Spy
  private ArrayList<SupplierSearchStrategy> supplierSearchStrategies;

  @Mock
  private SupplierSearchStrategy mockSearchStrategy;

  @Mock
  private BusyFlightsRequest mockRequest;

  @InjectMocks
  private BusyFlightsSyncSearchService busyFlightsSyncSearchService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    doReturn(Arrays.asList(BusyFlightsResponse.builder().build())).when(mockSearchStrategy).search(eq(mockRequest));
    supplierSearchStrategies.add(mockSearchStrategy);
  }

  @Test
  public void testSearchFlights() throws Exception {
    List<BusyFlightsResponse> result =
        busyFlightsSyncSearchService.searchFlights(mockRequest);
    verify(mockSearchStrategy, times(1)).search(any(BusyFlightsRequest.class));
    Assert.assertEquals(1, result.size());
  }
}