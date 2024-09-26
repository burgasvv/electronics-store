package org.burgas.employeeservice.repository;

import org.burgas.employeeservice.entity.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select e.* from employee e join purchase p on e.id = p.employee_id where p.id = ?1
                    """
    )
    Optional<Employee> findEmployeeByPurchaseId(Long purchaseId);

    List<Employee> findEmployeesByStoreId(Long storeId);

    Page<Employee> findEmployeesByStoreId(Long storeId, @NotNull Pageable pageable);

    Page<Employee> findEmployeesByPositionId(Long positionId, Pageable pageable);
}
