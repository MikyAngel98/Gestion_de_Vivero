package com.Vivero.Canavalia.dto;

import lombok.Data;

@Data
public class PlantinDTO {
    private Integer plantinId; // ID del plantín
    private Integer areaCultivoId; // ID del área de cultivo
    private Integer cantidad; // Cantidad de plantines ingresados
    private String tamaño; // Tamaño del plantín ("Pequeño", "Grande", etc.)
}

