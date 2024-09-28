package org.burgas.purchaseservice.repository;

import org.burgas.purchaseservice.entity.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTypeRepository extends JpaRepository<PurchaseType, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    update purchase set purchase_type_id = null where purchase_type_id = ?1
                    """
    )
    @Modifying
    void updatePurchasesByPurchaseTypeId(Long purchaseTypeId);
}
