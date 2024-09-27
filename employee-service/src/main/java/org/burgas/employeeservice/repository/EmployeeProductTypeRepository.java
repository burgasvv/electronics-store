package org.burgas.employeeservice.repository;

import org.burgas.employeeservice.entity.EmployeeProductType;
import org.burgas.employeeservice.entity.EmployeeProductTypePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeProductTypeRepository
        extends JpaRepository<EmployeeProductType, EmployeeProductTypePK> {

    void deleteEmployeeProductTypesByEmployeeId(Long employeeId);

    @Query(
            nativeQuery = true,
            value = """
                    select ep.product_type_id from employee_product_type ep
                    where ep.employee_id = ?1
                    """
    )
    List<Long> findEmployeeProductTypesIdsByEmployeeId(Long employeeId);
}
