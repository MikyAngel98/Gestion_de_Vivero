package com.Vivero.Canavalia.servicios;


import com.Vivero.Canavalia.dto.IngresoDTO;
import com.Vivero.Canavalia.dto.PlantinDTO;
import com.Vivero.Canavalia.modelo.*;
import com.Vivero.Canavalia.repositorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngresoServicio {

    private final IngresoRepositorio ingresoRepositorio;
    private final PlantinRepositorio plantinRepositorio;
    private final AreaCultivoRepositorio areaCultivoRepositorio;
    private final PlantinAreaCultivoRepositorio plantinAreaCultivoRepositorio;
    private final TipoMovimientoRepositorio tipoMovimientoRepositorio;
    private final HistorialMovimientoServicio historialMovimientoServicio;

    @Transactional
    public void registrarIngreso(IngresoDTO datosRecibidos) {
        // Registrar el ingreso y obtener el objeto guardado
        Ingreso ingreso = crearIngreso(datosRecibidos);

        // Procesar cada plantín del ingreso
        for (PlantinDTO plantinDTO : datosRecibidos.getPlantines()) {
            actualizarStockPlantin(plantinDTO);
            historialMovimientoServicio.registrarHistorialMovimiento(ingreso, plantinDTO);
        }
    }

    /**
     * Registra un nuevo ingreso en la base de datos.
     */
    private Ingreso crearIngreso(IngresoDTO datosRecibidos) {
        TipoMovimiento tipoMovimiento = tipoMovimientoRepositorio.findById(datosRecibidos.getTipoMovimientoId())
                .orElseThrow(() -> new RuntimeException("Tipo de movimiento no encontrado"));

        Ingreso ingreso = new Ingreso();
        ingreso.setDescripcion(datosRecibidos.getDescripcion());
        ingreso.setTipoMovimiento(tipoMovimiento);
        return ingresoRepositorio.save(ingreso);
    }

    /**
     * Actualiza el stock de un plantín en el área de cultivo correspondiente.
     */
    private void actualizarStockPlantin(PlantinDTO plantinDTO) {
        Plantin plantin = plantinRepositorio.findById(plantinDTO.getPlantinId())
                .orElseThrow(() -> new RuntimeException("Plantín no encontrado"));

        AreaCultivo areaCultivo = areaCultivoRepositorio.findById(plantinDTO.getAreaCultivoId())
                .orElseThrow(() -> new RuntimeException("Área de cultivo no encontrada"));

        PlantinAreaCultivo plantinArea = plantinAreaCultivoRepositorio
                .findByPlantinAndAreaCultivoAndTamaño(plantin, areaCultivo, plantinDTO.getTamaño())
                .orElse(new PlantinAreaCultivo());

        if (plantinArea.getId() == null) {
            plantinArea.setPlantin(plantin);
            plantinArea.setAreaCultivo(areaCultivo);
            plantinArea.setTamaño(plantinDTO.getTamaño());
            plantinArea.setStock(0);
        }

        plantinArea.setStock(plantinArea.getStock() + plantinDTO.getCantidad());

        plantinAreaCultivoRepositorio.save(plantinArea);
    }

    /**
     * Registra el historial del movimiento del ingreso de un plantín.

    private void registrarHistorialMovimiento(Ingreso ingreso, PlantinIngresoDTO plantinDTO) {
        Plantin plantin = plantinRepositorio.findById(plantinDTO.getPlantinId())
                .orElseThrow(() -> new RuntimeException("Plantín no encontrado"));

        TipoMovimiento tipoMovimiento = ingreso.getTipoMovimiento();

        HistorialMovimiento historial = new HistorialMovimiento();
        historial.setPlantinNombre(plantin.getNombre());
        historial.setTipoMovimiento(tipoMovimiento.getNombre());
        historial.setCantidad(plantinDTO.getCantidad());
        historial.setFechaMovimiento(LocalDateTime.now());
        historial.setIngresoId(ingreso.getId());
        historial.setTamaño(plantinDTO.getTamaño());

        historialMovimientoRepositorio.save(historial);
    }*/


}
