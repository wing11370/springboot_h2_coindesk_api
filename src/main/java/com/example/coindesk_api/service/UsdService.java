package com.example.coindesk_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.UsdRecord;
import com.example.coindesk_api.repository.UsdRepository;

@Service
public class UsdService {
    @Autowired
    UsdRepository usdRepository;

    public CurrencyRecord save(String dateString, Double rate) {
        UsdRecord usd = new UsdRecord();
        usd.setDate(dateString);
        usd.setRate(rate);
        usdRepository.save(usd);
        return usd;
    }

    public Iterable<UsdRecord> findAll() {
        final Iterable<UsdRecord> recordList = usdRepository.findAll();
        return recordList;
    }

    public CurrencyRecord findById(Integer id) {
        Optional<UsdRecord> record = usdRepository.findById(id);
        if (!record.isPresent()) {
            return null;
        }
        return record.get();
    }

    public CurrencyRecord update(Integer id, CurrencyRecord currencyRecord) {
        try {
            UsdRecord record = usdRepository.findById(id).get();
            String date = currencyRecord.getDate();
            if (record.getDate() != date) {
                record.setDate(date);
            }
            double rate = currencyRecord.getRate();
            if (record.getRate() != rate) {
                record.setRate(rate);
            }
            return usdRepository.save(record);
        } catch (Exception exception) {
            return null;
        }
    }

    public Boolean deleteById(Integer id) {
        try {
            Optional<UsdRecord> record = usdRepository.findById(id);
            if (!record.isPresent()) {
                return null;
            }
            usdRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public CurrencyRecord findByDate(String dateString) {
        Optional<CurrencyRecord> findByDateResult = usdRepository.findByDate(dateString);
        if (findByDateResult.isPresent()) {
            CurrencyRecord record = findByDateResult.get();
            return record;
        }
        return null;
    }

}
