package com.coop.migration.schema_intake.persistence.repo;

import com.coop.migration.schema_intake.persistence.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColumnRepository extends JpaRepository<ColumnEntity, UUID> {
}
