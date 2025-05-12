package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.dto.DetalleSalidaDTO;
import com.Vivero.Canavalia.dto.PlantinIngresoDTO;
import com.Vivero.Canavalia.modelo.*;
import com.Vivero.Canavalia.repositorio.HistorialMovimientoRepositorio;
import com.Vivero.Canavalia.repositorio.PlantinAreaCultivoRepositorio;
import com.Vivero.Canavalia.repositorio.PlantinRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistorialMovimientoServicio {

    private final PlantinRepositorio plantinRepositorio;
    private final HistorialMovimientoRepositorio historialMovimientoRepositorio;
    private final PlantinAreaCultivoRepositorio plantinAreaCultivo;

    /**
     * Registra el historial del movimiento del plantín.
     */
    public void registrarHistorialMovimientoingreso(Ingreso ingreso, PlantinIngresoDTO plantinIngresoDTO) {

        Plantin plantin = plantinRepositorio.findById(plantinIngresoDTO.getPlantinId())
                .orElseThrow(() -> new RuntimeException("Plantín no encontrado"));

        TipoMovimiento tipoMovimiento = ingreso.getTipoMovimiento();

        HistorialMovimiento historial = new HistorialMovimiento();
        historial.setPlantinNombre(plantin.getNombre());
        historial.setTipoMovimiento(tipoMovimiento.getNombre());
        historial.setCantidad(plantinIngresoDTO.getCantidad());
        historial.setFechaMovimiento(LocalDateTime.now());
        historial.setMovimientoId(ingreso.getId());
        historial.setTamaño(plantinIngresoDTO.getTamaño());

        historialMovimientoRepositorio.save(historial);
    }

    public void registrarHistorialMovimientoSalida(Salida salida, DetalleSalidaDTO detalleSalidaDTO){

        PlantinAreaCultivo inventario = plantinAreaCultivo.findById(detalleSalidaDTO.getIdPlantinAreaCultivo())
                .orElseThrow(() -> new RuntimeException("No se encontró el plantín con ID: " + detalleSalidaDTO.getIdPlantinAreaCultivo()));

        TipoMovimiento tipoMovimiento = salida.getTipoMovimiento(); // obtenemos el tipo de movimiento
        Plantin plantin = inventario.getPlantin(); // optenemos el plantin

        HistorialMovimiento historial = new HistorialMovimiento();

        historial.setPlantinNombre(plantin.getNombre());
        historial.setCantidad(detalleSalidaDTO.getCantidad());
        historial.setTamaño(inventario.getTamaño());

        historial.setTipoMovimiento(tipoMovimiento.getNombre());

        historial.setFechaMovimiento(salida.getFechaSalida());
        historial.setMovimientoId(salida.getId());

        historialMovimientoRepositorio.save(historial);
    }
}
