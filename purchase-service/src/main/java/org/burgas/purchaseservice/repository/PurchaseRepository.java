package org.burgas.purchaseservice.repository;

import org.burgas.purchaseservice.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findPurchasesByEmployeeId(Long employeeId);

    @Query(
            nativeQuery = true,
            value = """
                        update product_store set amount = amount - 1 where store_id = ?1 and product_id = ?2
                    """
    )
    @Modifying
    void updateProductInStoreAmount(Long storeId, Long productId);

    @Query(
            nativeQuery = true,
            value = """
                        select e.id from employee e
                        join employee_product_type ept on e.id = ept.employee_id
                        join product p on ept.product_type_id = p.product_type_id
                        where p.id = ?1
                    """
    )
    List<Long> findEmployeeIdsByAProductType(Long productId);
}
