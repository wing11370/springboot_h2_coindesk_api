package com.example.coindesk_api.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.coindesk_api.dto.CurrencyDto;
import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.CurrencyRecordImpl;
import com.example.coindesk_api.model.EurRecord;
import com.example.coindesk_api.model.GbpRecord;
import com.example.coindesk_api.model.UsdRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyService {

    @Autowired
    UsdService usdService;

    @Autowired
    GbpService gbpService;

    @Autowired
    EurService eurService;
    @Autowired
    CoindeskService coindeskService;

    private CurrencyDto wrapInfo(String currency, CurrencyRecord currencyRecord) {
        if (currencyRecord == null) {
            return null;
        }
        Integer id = currencyRecord.getId();
        String date = currencyRecord.getDate();
        double rate = currencyRecord.getRate();
        CurrencyDto currencyDto = new CurrencyDto(id, date, currency, rate);
        return currencyDto;
    }

    public String createAllCurrencyRecord() throws JsonProcessingException {

        JsonNode root = coindeskService.fetchCoindesk();
        String updatedISO = root.path("time").path("updatedISO").asText();
        String dateString = coindeskService.dateTransform(updatedISO);

        JsonNode bpiNode = root.path("bpi");

        CurrencyRecord findByDateUsdResult = usdService.findByDate(dateString);
        if (findByDateUsdResult == null) {
            Double usdRate = Double.parseDouble(bpiNode.path("USD").path("rate_float").asText());
            usdService.save(dateString, usdRate);
        }
        CurrencyRecord findByDateGbpResult = gbpService.findByDate(dateString);
        if (findByDateGbpResult == null) {
            Double gbpRate = Double.parseDouble(bpiNode.path("GBP").path("rate_float").asText());
            gbpService.save(dateString, gbpRate);
        }
        CurrencyRecord findByDateEurResult = eurService.findByDate(dateString);
        if (findByDateEurResult == null) {
            Double eurRate = Double.parseDouble(bpiNode.path("EUR").path("rate_float").asText());
            eurService.save(dateString, eurRate);
        }

        return "ok";
    }

    public CurrencyDto create(String currency) throws JsonMappingException, JsonProcessingException {
        JsonNode root = coindeskService.fetchCoindesk();

        String updatedISO = root.path("time").path("updatedISO").asText();
        String dateString = coindeskService.dateTransform(updatedISO);

        JsonNode bpiNode = root.path("bpi");

        CurrencyRecord record = null;
        CurrencyRecord findByDateResult = null;
        switch (currency) {
            case "usd":
                findByDateResult = usdService.findByDate(dateString);
                if (findByDateResult != null) {
                    record = findByDateResult;
                    break;
                }
                Double usdRate = Double.parseDouble(bpiNode.path("USD").path("rate_float").asText());
                record = usdService.save(dateString, usdRate);
                break;
            case "gbp":
                findByDateResult = gbpService.findByDate(dateString);
                if (findByDateResult != null) {
                    record = findByDateResult;
                    break;
                }

                Double gbpRate = Double.parseDouble(bpiNode.path("GBP").path("rate_float").asText());
                record = gbpService.save(dateString, gbpRate);
                break;
            case "eur":
                findByDateResult = eurService.findByDate(dateString);
                if (findByDateResult != null) {
                    record = findByDateResult;
                    break;
                }
                Double eurRate = Double.parseDouble(bpiNode.path("EUR").path("rate_float").asText());
                record = eurService.save(dateString, eurRate);
                break;
            default:
                break;
        }

        return wrapInfo(currency, record);
    }

    public Iterable<?> findAll(String currency) {
        Iterable<?> records = null;
        ArrayList<CurrencyDto> output = new ArrayList<>();
        switch (currency) {
            case "usd":
                records = usdService.findAll();
                for (Object record : records) {
                    output.add(wrapInfo(currency, (UsdRecord) record));
                }
                break;
            case "gbp":
                records = gbpService.findAll();
                for (Object record : records) {
                    output.add(wrapInfo(currency, (GbpRecord) record));
                }
                break;
            case "eur":
                records = eurService.findAll();
                for (Object record : records) {
                    output.add(wrapInfo(currency, (EurRecord) record));
                }
                break;
            default:
                break;
        }

        return output;
    }

    public CurrencyDto findById(String currency, Integer id) {
        CurrencyRecord record = null;
        switch (currency) {
            case "usd":
                record = usdService.findById(id);
                break;
            case "gbp":
                record = gbpService.findById(id);
                break;
            case "eur":
                record = eurService.findById(id);
                break;
            default:
                break;
        }
        return wrapInfo(currency, record);
    }

    public CurrencyDto updateUsd(Integer id, UsdRecord usdRecord) {
        String currency = "usd";
        CurrencyRecord record = usdService.update(id, usdRecord);
        return wrapInfo(currency, record);
    }

    public CurrencyDto updateGbp(Integer id, GbpRecord gbpRecord) {
        String currency = "gbp";
        CurrencyRecord record = gbpService.update(id, gbpRecord);
        return wrapInfo(currency, record);
    }

    public CurrencyDto updateEur(Integer id, EurRecord eurRecord) {
        String currency = "eur";
        CurrencyRecord record = eurService.update(id, eurRecord);
        return wrapInfo(currency, record);
    }

    public CurrencyDto update(String currency, Integer id, CurrencyRecordImpl currencyRecord) {
        CurrencyRecord record = null;
        switch (currency) {
            case "usd":
                record = usdService.update(id, currencyRecord);
                break;
            case "gbp":
                record = gbpService.update(id, currencyRecord);
                break;
            case "eur":
                record = eurService.update(id, currencyRecord);
                break;
            default:
                break;
        }
        return wrapInfo(currency, record);
    }

    public Boolean deleteById(String currency, Integer id) {
        Boolean result = false;
        switch (currency) {
            case "usd":
                result = usdService.deleteById(id);
                break;
            case "gbp":
                result = gbpService.deleteById(id);
                break;
            case "eur":
                result = eurService.deleteById(id);
                break;
            default:
                break;
        }
        return result;
    }

    public String fetchCoindesk() throws JsonMappingException, JsonProcessingException {
        return coindeskService.fetchCoindesk().toString();
    }

    public CurrencyDto dataTransform(String currency, CurrencyRecord currencyRecord) {
        return wrapInfo(currency, currencyRecord);
    }
}
