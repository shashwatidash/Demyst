package com.example.demyst.repository;

import com.example.demyst.model.BusinessDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountingRepository extends JpaRepository<BusinessDetails, Long> {
    @Query("SELECT s FROM BusinessDetails s WHERE s.businessId= ?1")
    Optional<BusinessDetails> findBusinessDetailsById(long businessId);
}
