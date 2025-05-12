package com.Vivero.Canavalia.servicios;


import com.Vivero.Canavalia.dto.IngresoDTO;
import com.Vivero.Canavalia.dto.PlantinIngresoDTO;
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

        if (datosRecibidos.getPlantines() == null || datosRecibidos.getPlantines().isEmpty()) {
            throw new RuntimeException("Debe tener al menos un Plantin para Registrar");
        }

        // Registrar el ingreso y obtener el objeto guardado
        Ingreso ingreso = crearIngreso(datosRecibidos);

        // Procesar cada plantín del ingreso
        for (PlantinIngresoDTO plantinIngresoDTO : datosRecibidos.getPlantines()) {
            actualizarStockPlantin(plantinIngresoDTO);
            historialMovimientoServicio.registrarHistorialMovimientoingreso(ingreso, plantinIngresoDTO);
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
    private void actualizarStockPlantin(PlantinIngresoDTO plantinIngresoDTO) {
        Plantin plantin = plantinRepositorio.findById(plantinIngresoDTO.getPlantinId())
                .orElseThrow(() -> new RuntimeException("Plantín no encontrado"));

        AreaCultivo areaCultivo = areaCultivoRepositorio.findById(plantinIngresoDTO.getAreaCultivoId())
                .orElseThrow(() -> new RuntimeException("Área de cultivo no encontrada"));

        PlantinAreaCultivo plantinArea = plantinAreaCultivoRepositorio
                .findByPlantinAndAreaCultivoAndTamaño(plantin, areaCultivo, plantinIngresoDTO.getTamaño())
                .orElse(new PlantinAreaCultivo());

        if (plantinArea.getId() == null) {
            plantinArea.setPlantin(plantin); //inserta el plantin
            plantinArea.setAreaCultivo(areaCultivo);//inserta el area de cultivo
            plantinArea.setTamaño(plantinIngresoDTO.getTamaño()); // ingresa el tamaño del plantin entrante
            plantinArea.setStock(0);
        }

        plantinArea.setStock(plantinArea.getStock() + plantinIngresoDTO.getCantidad()); //aumenta la cantidad del plantin
        plantin.setStockActual(plantin.getStockActual() + plantinIngresoDTO.getCantidad());

        plantinRepositorio.save(plantin); //incrementa el stock total del plantin
        plantinAreaCultivoRepositorio.save(plantinArea);
    }
    


}
