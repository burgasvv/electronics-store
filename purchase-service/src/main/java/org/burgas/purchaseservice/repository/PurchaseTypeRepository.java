package org.burgas.purchaseservice.repository;

import org.burgas.purchaseservice.entity.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {
}
