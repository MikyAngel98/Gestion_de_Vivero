package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.dto.DetalleSalidaDTO;
import com.Vivero.Canavalia.dto.SalidaDTO;
import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.modelo.Salida;
import com.Vivero.Canavalia.repositorio.PlantinRepositorio;
import com.Vivero.Canavalia.repositorio.SalidaRepositorio;
import com.Vivero.Canavalia.repositorio.TipoMovimientoRepositorio;
import com.Vivero.Canavalia.modelo.PlantinAreaCultivo;
import com.Vivero.Canavalia.modelo.TipoMovimiento;
import com.Vivero.Canavalia.repositorio.PlantinAreaCultivoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalidaServicio {

    private final PlantinAreaCultivoRepositorio plantinAreaCultivo;
    private final SalidaRepositorio salidaRepositorio;
    private final TipoMovimientoRepositorio tipoMovimientoRepositorio;
    private final PlantinRepositorio plantinRepositorio;

    private void registrarSalida(SalidaDTO datosRecibidos){



    }

    private Salida crearSalida(SalidaDTO datosRecibidos){

        TipoMovimiento tipoMovimiento = tipoMovimientoRepositorio.findById(datosRecibidos.getTipoMovimientoId())
                .orElseThrow(() -> new RuntimeException("Tipo de movimiento no encontrado"));

        Salida salida = new Salida();
        salida.setDescripcion(datosRecibidos.getDescripcion());
        salida.setTipoMovimiento(tipoMovimiento);

        return salidaRepositorio.save(salida); //guarda la salida
    }

    private void actualizarStock (DetalleSalidaDTO detalle){

        PlantinAreaCultivo inventario = plantinAreaCultivo.findById(detalle.getIdPlantinAreaCultivo())
                .orElseThrow(() -> new RuntimeException("No se encontró el plantín con ID: " + detalle.getIdPlantinAreaCultivo()));

        Plantin plantin = inventario.getPlantin();

        if (inventario.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock del plantín: " + plantin.getId());
        }

        // Descontar y guardar
        inventario.setStock(inventario.getStock() - detalle.getCantidad());
        plantinAreaCultivo.save(inventario);
    }

}
