package com.travix.medusa.busyflights.supplier.client;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract client implementation of data supplier
 *
 * @param <REQ>
 * @param <RESP>
 */
@Slf4j
public abstract class AbstractSupplierClient<REQ, RESP> {

  @Resource(name = "supplierRestTemplate")
  private RestTemplate restTemplate;

  @Resource private ObjectMapper objectMapper;

  public List<RESP> get(final REQ req) {

    final URI uri = getUri(req);
    log.debug("calling the uri {}", uri.toString());

    RESP[] response = restTemplate.getForObject(uri, getResponseClass());

    log.debug("response size: {}", response.length);

    return Arrays.asList(response);
  }

  protected abstract Class<RESP[]> getResponseClass();

  protected URI getUri(final REQ req) {
    final String endpoint = getEndpoint();
    Map<String, String> map =
        objectMapper.convertValue(req, new TypeReference<Map<String, String>>() {});

    LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
    map.forEach(linkedMultiValueMap::add);
    return fromHttpUrl(endpoint).queryParams(linkedMultiValueMap).build().toUri();
  }

  abstract String getEndpoint();
}
