package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
    List<CurrencyData> findAllByCurrencyAndTimestampBetween(String currency, Long from, Long to);

    CurrencyData findFirstByCurrencyOrderByTimestampDesc(String currency);
}
