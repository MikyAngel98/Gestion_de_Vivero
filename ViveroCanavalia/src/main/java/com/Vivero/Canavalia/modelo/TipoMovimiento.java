package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TipoMovimiento")
@Data
public class TipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String nombre;
}
