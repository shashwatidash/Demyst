package com.example.demyst.repository;

import com.example.demyst.model.BalanceSheetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceSheetItemRepository extends JpaRepository<BalanceSheetItem, Long> {
}
