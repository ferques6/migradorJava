package com.coop.migration.schema_intake;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.coop.migration.schema_intake.api.dto.TableDTO;
import com.coop.migration.schema_intake.api.dto.ColumnDTO;
import com.coop.migration.schema_intake.api.dto.ForeignKeyDTO;
import com.coop.migration.schema_intake.api.dto.SchemaDTO;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RestController
public class HealthController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // NUEVO: devuelve un ColumnDTO de ejemplo en JSON
    @GetMapping("/sample/column")
    public ColumnDTO sampleColumn() {
        return ColumnDTO.builder()
                .name("id")
                .type("int")
                .nullable(false)
                .build();
    }

    @GetMapping("/sample/table")
    public TableDTO sampleTable() {
        // Creamos columnas
        ColumnDTO idCol = ColumnDTO.builder()
                .name("id")
                .type("int")
                .nullable(false)
                .build();

        ColumnDTO nameCol = ColumnDTO.builder()
                .name("name")
                .type("varchar(255)")
                .nullable(false)
                .build();

        List<ColumnDTO> columns = Arrays.asList(idCol, nameCol);

        // Retornamos tabla con sus columnas
        return TableDTO.builder()
                .name("users")
                .columns(columns)
                .build();
    }

    @GetMapping("/sample/schema")
    public SchemaDTO sampleSchema() {

        // Tabla socios
        var socios = TableDTO.builder()
                .name("socios")
                .columns(List.of(
                        ColumnDTO.builder().name("id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("nombre").type("varchar(255)").nullable(false).build()
                ))
                .build();

        // Tabla medidores
        var medidores = TableDTO.builder()
                .name("medidores")
                .columns(List.of(
                        ColumnDTO.builder().name("id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("socio_id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("numero").type("varchar(50)").nullable(false).build()
                ))
                .build();

        // FK medidores.socio_id -> socios.id
        var fk = ForeignKeyDTO.builder()
                .fromTable("medidores").fromColumn("socio_id")
                .toTable("socios").toColumn("id")
                .build();

        return SchemaDTO.builder()
                .id(UUID.randomUUID())
                .engine("sqlserver")
                .vendorGuess(null)
                .tables(Arrays.asList(socios, medidores))
                .foreignKeys(List.of(fk))
                .build();
    }

    @PostMapping("/columns")
    public ColumnDTO createColumn(@Valid @RequestBody ColumnDTO dto) {
        // Si llega acá, pasó las validaciones (@NotBlank en name y type)
        // Lo devolvemos tal cual para ver que funcionó.
        return dto;
    }
}
