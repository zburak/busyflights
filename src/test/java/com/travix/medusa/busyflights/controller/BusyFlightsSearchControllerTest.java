package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.FlightSearchService;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class BusyFlightsSearchControllerTest {

  @Mock
  FlightSearchService flightSearchService;

  @InjectMocks
  BusyFlightsSearchController busyFlightsSearchController;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

  }


  @Test
  public void testSearch() throws Exception {

    final BusyFlightsRequest mockRequest = Mockito.mock(BusyFlightsRequest.class);
    when(flightSearchService.searchFlights(eq(mockRequest)))
        .thenReturn(Arrays.asList(BusyFlightsResponse.builder().build()));

    ResponseEntity<List<BusyFlightsResponse>> result =
        busyFlightsSearchController.search(mockRequest);

    verify(flightSearchService, times(1)).searchFlights(eq(mockRequest));
    Assert.assertEquals(200, result.getStatusCodeValue());
    Assert.assertEquals(1, result.getBody().size());
  }
}
