package com.coop.migration.schema_intake;

import com.coop.migration.schema_intake.api.dto.ColumnDTO;
import com.coop.migration.schema_intake.api.dto.ForeignKeyDTO;
import com.coop.migration.schema_intake.api.dto.SchemaDTO;
import com.coop.migration.schema_intake.api.dto.TableDTO;
import com.coop.migration.schema_intake.service.TableService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class HealthController {

    // 1) Campo e inyección primero (podés usar @RequiredArgsConstructor si querés)
    private final TableService tableService;
    public HealthController(TableService tableService) {
        this.tableService = tableService;
    }

    // 2) Endpoints básicos
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/columns")
    public ColumnDTO createColumn(@Valid @RequestBody ColumnDTO dto) {
        return dto; // validado por @Valid
    }

    @PostMapping("/tables")
    public TableDTO createTable(@Valid @RequestBody TableDTO dto) {
        return tableService.create(dto);
    }

    // 3) Demos (idealmente moverlos luego a un SampleController con @RequestMapping("/sample"))
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
        var idCol = ColumnDTO.builder().name("id").type("int").nullable(false).build();
        var nameCol = ColumnDTO.builder().name("name").type("varchar(255)").nullable(false).build();
        return TableDTO.builder()
                .name("users")
                .columns(List.of(idCol, nameCol))
                .build();
    }

    @GetMapping("/sample/schema")
    public SchemaDTO sampleSchema() {
        var socios = TableDTO.builder()
                .name("socios")
                .columns(List.of(
                        ColumnDTO.builder().name("id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("nombre").type("varchar(255)").nullable(false).build()
                )).build();

        var medidores = TableDTO.builder()
                .name("medidores")
                .columns(List.of(
                        ColumnDTO.builder().name("id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("socio_id").type("int").nullable(false).build(),
                        ColumnDTO.builder().name("numero").type("varchar(50)").nullable(false).build()
                )).build();

        var fk = ForeignKeyDTO.builder()
                .fromTable("medidores").fromColumn("socio_id")
                .toTable("socios").toColumn("id")
                .build();

        return SchemaDTO.builder()
                .id(UUID.randomUUID())
                .engine("sqlserver")
                .vendorGuess(null)
                .tables(List.of(socios, medidores))
                .foreignKeys(List.of(fk))
                .build();
    }
}
