package com.example.coindesk_api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.EurRecord;

public interface EurRepository extends CrudRepository<EurRecord, Integer> {

    Optional<CurrencyRecord> findByDate(String dateString);

}
