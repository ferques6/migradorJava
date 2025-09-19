package com.coop.migration.schema_intake;

import com.coop.migration.schema_intake.api.dto.ColumnDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
