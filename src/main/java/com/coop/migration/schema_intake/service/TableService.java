package com.coop.migration.schema_intake.service;

import com.coop.migration.schema_intake.api.dto.TableDTO;
import com.coop.migration.schema_intake.api.mapper.TableMapper;
import com.coop.migration.schema_intake.persistence.entity.TableEntity;
import com.coop.migration.schema_intake.persistence.repo.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Transactional
    public TableDTO create(TableDTO dto) {
        // 1) DTO -> Entity
        TableEntity entity = TableMapper.toEntity(dto);
        // 2) Guardar
        TableEntity saved = tableRepository.save(entity);
        // 3) Entity -> DTO (respuesta)
        return TableMapper.toDTO(saved);
    }
}
