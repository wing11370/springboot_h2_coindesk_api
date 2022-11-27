package com.example.coindesk_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.GbpRecord;
import com.example.coindesk_api.repository.GbpRepository;

@Service
public class GbpService {
    @Autowired
    GbpRepository gbpRepository;

    public CurrencyRecord save(String dateString, Double rate) {
        GbpRecord gbp = new GbpRecord();
        gbp.setDate(dateString);
        gbp.setRate(rate);
        gbpRepository.save(gbp);
        return gbp;
    }

    public Iterable<GbpRecord> findAll() {
        Iterable<GbpRecord> recordList = gbpRepository.findAll();
        return recordList;
    }

    public CurrencyRecord findById(Integer id) {
        Optional<GbpRecord> record = gbpRepository.findById(id);
        if (!record.isPresent()) {
            return null;
        }
        return record.get();
    }

    public CurrencyRecord update(Integer id, CurrencyRecord currencyRecord) {
        try {
            GbpRecord record = gbpRepository.findById(id).get();
            String date = currencyRecord.getDate();
            if (record.getDate() != date) {
                record.setDate(date);
            }
            double rate = currencyRecord.getRate();
            if (record.getRate() != rate) {
                record.setRate(rate);
            }
            return gbpRepository.save(record);
        } catch (Exception exception) {
            return null;
        }
    }

    public Boolean deleteById(Integer id) {
        try {
            Optional<GbpRecord> record = gbpRepository.findById(id);
            if (!record.isPresent()) {
                return null;
            }
            gbpRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public CurrencyRecord findByDate(String dateString) {
        Optional<CurrencyRecord> findByDateResult = gbpRepository.findByDate(dateString);
        if (findByDateResult.isPresent()) {
            CurrencyRecord record = findByDateResult.get();
            return record;
        }
        return null;
    }

}
