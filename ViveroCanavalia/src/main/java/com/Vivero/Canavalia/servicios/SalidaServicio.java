package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.dto.DetalleSalidaDTO;
import com.Vivero.Canavalia.dto.SalidaDTO;
import com.Vivero.Canavalia.modelo.*;
import com.Vivero.Canavalia.repositorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalidaServicio {

    private final PlantinAreaCultivoRepositorio plantinAreaCultivo;
    private final SalidaRepositorio salidaRepositorio;
    private final TipoMovimientoRepositorio tipoMovimientoRepositorio;
    private final HistorialMovimientoServicio historialMovimientoServicio;


    @Transactional
    public void registrarSalida(SalidaDTO datosRecibidos){

        if (datosRecibidos.getDetalleSalida() == null || datosRecibidos.getDetalleSalida().isEmpty()) {
            throw new RuntimeException("La salida debe tener al menos un Plantin");
        }

        Salida salida = crearSalida(datosRecibidos);

        // se procesan los detalles de la salida
        for (DetalleSalidaDTO detalleSalida: datosRecibidos.getDetalleSalida()){

            actualizarStock(detalleSalida);//actualizar stock
            historialMovimientoServicio.registrarHistorialMovimientoSalida(salida,detalleSalida); // registrar historial
        }

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
                .orElseThrow(() -> new RuntimeException("No se encontraron datos con ID: " + detalle.getIdPlantinAreaCultivo()));

        Plantin plantin = inventario.getPlantin();

        if (inventario.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock del plantín: " + plantin.getId());
        }

        // Descontar y guardar
        inventario.setStock(inventario.getStock() - detalle.getCantidad());
        plantinAreaCultivo.save(inventario);
    }

}
