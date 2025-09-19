package com.coop.migration.schema_intake.api.dto;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SchemaDTO {

    private UUID id;                // lo usaremos más adelante cuando persistamos
    private String engine;          // ej: "sqlserver", "postgres"
    private String vendorGuess;     // ej: "A", "B", "C" (si lo conocemos)

    private List<TableDTO> tables;          // composición: 1..N tablas
    private List<ForeignKeyDTO> foreignKeys; // 0..N claves foráneas
}
