package org.burgas.productservice.repository;

import org.burgas.productservice.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Query(
            nativeQuery = true,
            value = """
                        select pt.* from product_type pt
                        join employee_product_type et on pt.id = et.product_type_id join employee e on e.id = et.employee_id
                        where e.id = ?1
                    """
    )
    List<ProductType> findTypeByEmployeeId(Long employeeId);
}
