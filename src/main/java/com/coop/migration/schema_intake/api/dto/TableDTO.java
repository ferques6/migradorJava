package com.coop.migration.schema_intake.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TableDTO {

    @NotBlank
    private String name;

    // Una tabla tiene muchas columnas
    private List<ColumnDTO> columns;
}
