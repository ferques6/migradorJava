package com.coop.migration.schema_intake.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ForeignKeyDTO {

    @NotBlank private String fromTable;   // tabla hija (donde está la FK)
    @NotBlank private String fromColumn;  // columna FK en la hija

    @NotBlank private String toTable;     // tabla padre (referenciada)
    @NotBlank private String toColumn;    // columna PK/única en la padre
}
