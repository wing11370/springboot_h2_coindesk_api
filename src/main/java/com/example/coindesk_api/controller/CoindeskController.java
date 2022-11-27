package com.example.coindesk_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk_api.dto.CurrencyDto;
import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.CurrencyRecordImpl;
import com.example.coindesk_api.model.EurRecord;
import com.example.coindesk_api.model.GbpRecord;
import com.example.coindesk_api.model.UsdRecord;
import com.example.coindesk_api.service.CurrencyService;
import com.example.coindesk_api.service.EurService;
import com.example.coindesk_api.service.GbpService;
import com.example.coindesk_api.service.UsdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class CoindeskController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    UsdService usdService;

    @Autowired
    GbpService gbpService;

    @Autowired
    EurService eurService;

    @PostMapping("/{currency}")
    public CurrencyDto create(@PathVariable String currency) throws JsonMappingException, JsonProcessingException {
        return currencyService.create(currency);
    }

    @PostMapping("/")
    public String createAllCurrencyRecord()
            throws JsonMappingException, JsonProcessingException {
        return currencyService.createAllCurrencyRecord();
    }

    @GetMapping("/{currency}")
    public Iterable<?> findAll(@PathVariable String currency) {
        return currencyService.findAll(currency);
    }

    @GetMapping("/{currency}/{id}")
    public CurrencyDto findById(@PathVariable String currency, @PathVariable Integer id) {
        return currencyService.findById(currency, id);
    }

    // @PatchMapping("/{currency}/{id}")
    // public CurrencyDto update(@PathVariable String currency, @PathVariable
    // Integer id,
    // @RequestBody CurrencyRecordImpl currencyRecord) {
    // return currencyService.update(currency, id, currencyRecord);
    // }
    @PatchMapping("/usd/{id}")
    public CurrencyDto updateUsd(@PathVariable Integer id,
            @RequestBody UsdRecord usdRecord) {
        return currencyService.updateUsd(id, usdRecord);
    }

    @PatchMapping("/gbp/{id}")
    public CurrencyDto updateGbp(@PathVariable Integer id,
            @RequestBody GbpRecord gbpRecord) {
        return currencyService.updateGbp(id, gbpRecord);
    }

    @PatchMapping("/eur/{id}")
    public CurrencyDto updateEur(@PathVariable Integer id,
            @RequestBody EurRecord eurRecord) {
        return currencyService.updateEur(id, eurRecord);
    }

    @DeleteMapping("/{currency}/{id}")
    public Boolean deleteById(@PathVariable String currency, @PathVariable Integer id) {
        return currencyService.deleteById(currency, id);
    }

    @GetMapping("/fetchCoindesk")
    public String fetchCoindesk() throws JsonMappingException, JsonProcessingException {
        return currencyService.fetchCoindesk();
    }

    @PostMapping("/dataTransform/usd")
    public CurrencyDto dataTransformUsd(@RequestBody UsdRecord usdRecord) {
        return currencyService.dataTransform("usd", usdRecord);
    }

    @PostMapping("/dataTransform/gbp")
    public CurrencyDto dataTransformGbp(@RequestBody GbpRecord gbpRecord) {
        return currencyService.dataTransform("gbp", gbpRecord);
    }

    @PostMapping("/dataTransform/eur")
    public CurrencyDto dataTransformEur(@RequestBody EurRecord eurRecord) {
        return currencyService.dataTransform("eur", eurRecord);
    }

}
