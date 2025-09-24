package com.coop.migration.schema_intake.api.mapper;

import com.coop.migration.schema_intake.api.dto.ColumnDTO;
import com.coop.migration.schema_intake.api.dto.TableDTO;
import com.coop.migration.schema_intake.persistence.entity.ColumnEntity;
import com.coop.migration.schema_intake.persistence.entity.TableEntity;

import java.util.ArrayList;
import java.util.List;

public class TableMapper {

    // DTO -> Entity (setea relación padre-hijo)
    public static TableEntity toEntity(TableDTO dto) {
        TableEntity table = TableEntity.builder()
                .name(dto.getName())
                .build();

        List<ColumnEntity> cols = new ArrayList<>();
        if (dto.getColumns() != null) {
            for (ColumnDTO c : dto.getColumns()) {
                ColumnEntity ce = ColumnEntity.builder()
                        .name(c.getName())
                        .typeName(c.getType())
                        .nullable(c.isNullable())
                        .table(table)               // MUY IMPORTANTE: setear la referencia al padre
                        .build();
                cols.add(ce);
            }
        }
        table.setColumns(cols);
        return table;
        // Nota: gracias a cascade=ALL en TableEntity, al guardar table se guardan sus columnas.
    }

    // Entity -> DTO (para respuestas)
    public static TableDTO toDTO(TableEntity entity) {
        List<ColumnDTO> cols = new ArrayList<>();
        if (entity.getColumns() != null) {
            entity.getColumns().forEach(ce -> {
                cols.add(ColumnDTO.builder()
                        .name(ce.getName())
                        .type(ce.getTypeName())
                        .nullable(ce.isNullable())
                        .build());
            });
        }
        return TableDTO.builder()
                .name(entity.getName())
                .columns(cols)
                .build();
    }
}
