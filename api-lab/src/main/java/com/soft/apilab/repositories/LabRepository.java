package com.soft.apilab.repositories;

import com.soft.apilab.models.Lab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LabRepository extends JpaRepository<Lab, UUID>, JpaSpecificationExecutor<Lab> {
    Page<Lab> findAll(Specification<Lab> spec, Pageable pageable);
}
