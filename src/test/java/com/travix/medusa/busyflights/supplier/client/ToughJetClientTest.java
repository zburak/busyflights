package com.travix.medusa.busyflights.supplier.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

public class ToughJetClientTest {

  @Mock
  RestTemplate restTemplate;

  @Mock
  ObjectMapper objectMapper;

  @InjectMocks
  ToughJetClient toughJetClient;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    doReturn(new HashMap<String, String>())
        .when(objectMapper)
        .convertValue(any(ToughJetRequest.class), any(TypeReference.class));

    doReturn(new ToughJetResponse[]{ToughJetResponse.builder().build()}).when(restTemplate).getForObject(any(
        URI.class), eq(ToughJetResponse[].class));
  }


  @Test
  public void testGet() throws Exception {
    ReflectionTestUtils.setField(
        toughJetClient, "endpoint", "http://localhost:9001");

    List<ToughJetResponse> result = toughJetClient.get(ToughJetRequest.builder().build());

    verify(restTemplate, times(1)).getForObject(any(URI.class), any());
    Assert.assertEquals(1, result.size());
  }
}
