package com.travix.medusa.busyflights.supplier.client;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CrazyAirClientTest {

  @Mock
  RestTemplate restTemplate;

  @Mock
  ObjectMapper objectMapper;

  @InjectMocks
  CrazyAirClient crazyAirClient;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    doReturn(new HashMap<String, String>())
        .when(objectMapper)
        .convertValue(any(CrazyAirRequest.class), any(TypeReference.class));

    doReturn(new CrazyAirResponse[]{CrazyAirResponse.builder().build()}).when(restTemplate).getForObject(any(
        URI.class), eq(CrazyAirResponse[].class));
  }

  @Test
  public void testGet() throws Exception {
    ReflectionTestUtils.setField(
        crazyAirClient, "endpoint", "http://localhost:9001");

    List<CrazyAirResponse> result = crazyAirClient.get(CrazyAirRequest.builder().build());

    verify(restTemplate, times(1)).getForObject(any(URI.class), any());
    Assert.assertEquals(1, result.size());
  }
}
