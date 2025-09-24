package com.coop.migration.schema_intake.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_table") // evitamos usar "table" que es palabra reservada
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TableEntity {

    @Id
    @GeneratedValue
    private UUID id;                      // PK como UUID

    @Column(nullable = false, length = 120)
    private String name;

    // Relación 1 (tabla) -> N (columnas)
    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ColumnEntity> columns = new ArrayList<>();
}
