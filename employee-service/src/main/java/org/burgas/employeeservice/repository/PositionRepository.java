package org.burgas.employeeservice.repository;

import org.burgas.employeeservice.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    update employee set position_id = null where position_id = ?1
                    """
    )
    @Modifying
    void updateEmployeesByPositionId(Long positionId);
}
