package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialMovimientos")
@Data
public class HistorialMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plantin_nombre", nullable = false, length = 30)
    private String plantinNombre; // Ahora es un String en lugar de un Integer

    @Column(name = "tipo_movimiento", length = 25)
    private String tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    @Column(name = "ingreso_id")
    private Integer ingresoId;

    @Column(length = 25)
    private String tamaño;
}

