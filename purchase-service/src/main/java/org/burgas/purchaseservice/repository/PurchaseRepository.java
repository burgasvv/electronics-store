package org.burgas.purchaseservice.repository;

import org.burgas.purchaseservice.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findPurchasesByEmployeeId(Long employeeId);
}
