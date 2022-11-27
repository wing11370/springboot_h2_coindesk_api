package com.example.coindesk_api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.coindesk_api.model.CurrencyRecord;
import com.example.coindesk_api.model.UsdRecord;

public interface UsdRepository extends CrudRepository<UsdRecord, Integer> {

    Optional<CurrencyRecord> findByDate(String dateString);

}
