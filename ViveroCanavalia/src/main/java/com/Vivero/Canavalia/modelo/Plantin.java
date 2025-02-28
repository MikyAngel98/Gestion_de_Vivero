package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Plantin")
@Data
public class Plantin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual = 0;
}
