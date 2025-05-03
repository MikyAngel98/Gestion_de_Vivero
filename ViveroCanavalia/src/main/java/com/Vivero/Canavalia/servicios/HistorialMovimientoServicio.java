package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.dto.PlantinDTO;
import com.Vivero.Canavalia.modelo.HistorialMovimiento;
import com.Vivero.Canavalia.modelo.Ingreso;
import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.modelo.TipoMovimiento;
import com.Vivero.Canavalia.repositorio.HistorialMovimientoRepositorio;
import com.Vivero.Canavalia.repositorio.PlantinRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistorialMovimientoServicio {

    private final PlantinRepositorio plantinRepositorio;
    private final HistorialMovimientoRepositorio historialMovimientoRepositorio;

    /**
     * Registra el historial del movimiento del plantín.
     */
    public void registrarHistorialMovimiento(Ingreso ingreso, PlantinDTO plantinDTO) {

        Plantin plantin = plantinRepositorio.findById(plantinDTO.getPlantinId())
                .orElseThrow(() -> new RuntimeException("Plantín no encontrado"));

        TipoMovimiento tipoMovimiento = ingreso.getTipoMovimiento();

        HistorialMovimiento historial = new HistorialMovimiento();
        historial.setPlantinNombre(plantin.getNombre());
        historial.setTipoMovimiento(tipoMovimiento.getNombre());
        historial.setCantidad(plantinDTO.getCantidad());
        historial.setFechaMovimiento(LocalDateTime.now());
        historial.setMovimientoId(ingreso.getId());
        historial.setTamaño(plantinDTO.getTamaño());

        historialMovimientoRepositorio.save(historial);
    }
}
