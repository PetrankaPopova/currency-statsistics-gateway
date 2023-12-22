package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
}
