package com.Vivero.Canavalia.dto;

import lombok.Data;
import java.util.List;

@Data
public class IngresoDTO {
    private String descripcion; // Descripción del ingreso
    private Integer tipoMovimientoId; // ID del tipo de movimiento
    private List<PlantinDTO> plantines; // Lista de plantines ingresados
}

