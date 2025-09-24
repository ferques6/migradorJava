package com.coop.migration.schema_intake.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "app_column")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ColumnEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "type_name", nullable = false, length = 80)
    private String typeName;

    @Column(nullable = false)
    private boolean nullable;

    // Muchas columnas pertenecen a una tabla
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;
}
