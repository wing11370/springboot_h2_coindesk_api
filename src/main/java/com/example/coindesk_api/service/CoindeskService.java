package com.example.coindesk_api.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoindeskService {

    public JsonNode fetchCoindesk() throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        return root;
    }

    public String dateTransform(String updatedISO) {
        TemporalAccessor creationAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(updatedISO);
        Instant instant = Instant.from(creationAccessor);
        Date dateTime = Date.from(instant);

        String dateString = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(dateTime);
        return dateString;
    }
}
