package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Salida")
@Data
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_salida",nullable = false)
    private LocalDateTime fechaSalida = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "tipo_movimiento_id", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(length = 255)
    private String descripcion;
}
