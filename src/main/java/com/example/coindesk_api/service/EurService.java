package com.example.coindesk_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.EurRecord;
import com.example.coindesk_api.repository.EurRepository;

@Service
public class EurService {
    @Autowired
    EurRepository eurRepository;

    public CurrencyRecord save(String dateString, Double rate) {
        EurRecord eur = new EurRecord();
        eur.setDate(dateString);
        eur.setRate(rate);
        eurRepository.save(eur);
        return eur;
    }

    public Iterable<EurRecord> findAll() {
        Iterable<EurRecord> recordList = eurRepository.findAll();
        return recordList;
    }

    public CurrencyRecord findById(Integer id) {
        Optional<EurRecord> record = eurRepository.findById(id);
        if (!record.isPresent()) {
            return null;
        }
        return record.get();
    }

    public CurrencyRecord update(Integer id, CurrencyRecord currencyRecord) {
        try {
            EurRecord record = eurRepository.findById(id).get();
            String date = currencyRecord.getDate();
            if (record.getDate() != date) {
                record.setDate(date);
            }
            double rate = currencyRecord.getRate();
            if (record.getRate() != rate) {
                record.setRate(rate);
            }
            EurRecord result = eurRepository.save(record);
            return result;
        } catch (Exception exception) {
            return null;
        }
    }

    public Boolean deleteById(Integer id) {
        try {
            Optional<EurRecord> record = eurRepository.findById(id);
            if (!record.isPresent()) {
                return null;
            }
            eurRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public CurrencyRecord findByDate(String dateString) {
        Optional<CurrencyRecord> findByDateResult = eurRepository.findByDate(dateString);
        if (findByDateResult.isPresent()) {
            CurrencyRecord record = findByDateResult.get();
            return record;
        }
        return null;
    }
}
