package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * The RequestDetailsRepository interface is a Spring Data JPA repository responsible for CRUD operations on the
 * RequestDetails entity. It provides methods to query and manipulate request details records in the database.
 *
 * Annotations:
 * - @Repository: Indicates that this interface is a Spring bean and should be automatically detected during classpath
 *   scanning.
 *
 * Usage Example:
 * - Used to interact with the database and retrieve or manipulate request details records.
 */
@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {
}
