package com.Vivero.Canavalia.dto;

import lombok.Data;
import java.util.List;

@Data
public class IngresoRequestDTO {
    private String descripcion; // Descripción del ingreso
    private Integer tipoMovimientoId; // ID del tipo de movimiento
    private List<PlantinIngresoDTO> plantines; // Lista de plantines ingresados
}

