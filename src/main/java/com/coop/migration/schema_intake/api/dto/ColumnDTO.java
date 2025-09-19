package com.coop.migration.schema_intake.api.dto;

// Anotaciones para generar código y validar datos
import jakarta.validation.constraints.NotBlank;
import lombok.*;

// @Getter / @Setter: generan getters y setters.
// @NoArgsConstructor / @AllArgsConstructor: constructores vacío y completo.
// @Builder: nos permite construir objetos con un estilo fluido (ColumnDTO.builder()...build()).
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ColumnDTO {

    // @NotBlank: valida que el campo no venga vacío cuando este DTO se use como entrada.
    @NotBlank
    private String name;

    @NotBlank
    private String type;   // ejemplo: "int", "varchar", "date"

    private boolean nullable;
}
