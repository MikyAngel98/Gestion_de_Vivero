package com.Vivero.Canavalia.dto;

import lombok.Data;
import java.util.List;

@Data
public class SalidaDTO {
    private String descripcion; //descripcion de la Salida
    private Integer tipoMovimientoId; // Id del tipo de movimiento
    private List<DetalleSalidaDTO> detalleSalida; //Lista de plantines que salen

}
