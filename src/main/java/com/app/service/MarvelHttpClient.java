package com.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MarvelHttpClient {
    @Value("${api.key.public}")
    private String publicApiKey;
    @Value("${api.key.private}")
    private String privateApiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> sendGetRequest(String url) {

        return sendGetRequest(url, new LinkedMultiValueMap<>());
    }

    public ResponseEntity<String> sendGetRequest(String url, MultiValueMap<String, String> additionalParams) {

        String timestamp = "1618346588472";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", publicApiKey)
                .queryParam("ts", timestamp)
                .queryParam("hash", hash(publicApiKey, privateApiKey, timestamp))
                .queryParams(additionalParams);

        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                prepareRequestEntity(),
                String.class);
    }

    private HttpEntity<?> prepareRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private String hash(String publicApiKey, String privateApiKey, String timestamp) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String toHash = timestamp + privateApiKey + publicApiKey;
            return (new BigInteger(1, md.digest(toHash.getBytes()))).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
