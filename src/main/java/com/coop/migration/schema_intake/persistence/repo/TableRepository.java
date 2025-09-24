package com.coop.migration.schema_intake.persistence.repo;

import com.coop.migration.schema_intake.persistence.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, UUID> {
}
