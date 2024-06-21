package com.app.microcollector;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class MicroCollectorClient {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> getAllUsers() {
        return executeRequest();
    }

    private ResponseEntity<String> executeRequest() {
        try {
            log.info("Sending GET request to the WEG API: url={} ...", buildUri());
            ResponseEntity<String> response = restTemplate.exchange(buildUri(), HttpMethod.GET,
                    HttpEntity.EMPTY, String.class);
            log.info("WEG API responded with status code={}.", response.getStatusCode());
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException exc) {
            String message = String.format("Failed to call WEG API. Status code=%s, response body=%s.",
                    exc.getStatusCode(), exc.getResponseBodyAsString());
            log.error(message);
            throw new MicroRecipientException(message, exc);
        }
    }

    private URI buildUri() {
        return UriComponentsBuilder.fromUriString("http://micro-recipient:8081").path("/micro-recipient").buildAndExpand()
                .toUri();
    }

}
