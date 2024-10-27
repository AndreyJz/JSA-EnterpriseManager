package com.enterprisemanager.backend.infrastructure.repositories.serviceapproval;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enterprisemanager.backend.domain.entities.ServiceApproval;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceApprovalRepository extends CrudRepository<ServiceApproval, Long>{
    @Query("UPDATE ServiceApproval sa SET sa.approvalStatus.id = :status, sa.report = :report, sa.solution = :solution WHERE sa.approvalStatus.name = 'Pending' and sa.id = :id")
    Optional<ServiceApproval> updateServiceApprovalStatus(@Param("status") Long status, @Param("report") String report, @Param("solution") String solution, @Param("id") Long id);
}
