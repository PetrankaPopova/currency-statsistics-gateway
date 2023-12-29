package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
/**
 * The CurrencyDataRepository interface is a Spring Data JPA repository responsible for CRUD operations on the
 * CurrencyData entity. It provides methods to query and manipulate currency data records in the database.
 *
 * Custom Methods:
 * - findAllByCurrencyAndTimestampBetween: Retrieves a list of currency data records for a specific currency and within
 *   a specified timestamp range.
 * - findFirstByCurrencyOrderByTimestampDesc: Retrieves the most recent currency data record for a specific currency,
 *   ordered by timestamp in descending order.
 *
 * Annotations:
 * - @Repository: Indicates that this interface is a Spring bean and should be automatically detected during classpath
 *   scanning.
 *
 * Usage Example:
 * - Used to interact with the database and retrieve or manipulate currency data records.
 */
@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
    List<CurrencyData> findAllByCurrencyAndTimestampBetween(String currency, Long from, Long to);

    CurrencyData findFirstByCurrencyOrderByTimestampDesc(String currency);
}
