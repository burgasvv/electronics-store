package org.burgas.employeeservice.repository;

import org.burgas.employeeservice.entity.EmployeeProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProductTypeRepository extends JpaRepository<EmployeeProductType, Long> {
}
