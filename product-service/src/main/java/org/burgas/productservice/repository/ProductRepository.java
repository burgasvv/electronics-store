package org.burgas.productservice.repository;

import org.burgas.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            nativeQuery = true,
            value =
                """
                    select p.* from product p join purchase p2 on p.id = p2.product_id where p2.id = ?1
                """
    )
    Optional<Product> findProductByPurchaseId(Long purchaseId);

    @Query(
            nativeQuery = true,
            value = """
                        select p.* from product p
                        join purchase p2 on p.id = p2.product_id join employee e on e.id = p2.employee_id
                        where e.id = ?1 and p2.id = ?2
                    """
    )
    Optional<Product> findProductByEmployeeIdAndPurchaseId(Long employeeId, Long purchaseId);
}
