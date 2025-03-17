package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.repositorio.PlantinRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlantinServicios {
    @Autowired
    private PlantinRepositorio plantinRepositorio;

    public List<Plantin> listarTodos() {
        return plantinRepositorio.findAll();
    }

    public List<Plantin> buscarPlantinPorNombre(String nombre){
        return plantinRepositorio.findByNombreContaining(nombre);
    }

    //Mustra en que area de cultivo se encuentra un plantin
    public List<Map<String, Object>> verUbicacionDePlantin (String nombrePlantin){
        List<Object[]> resultado = plantinRepositorio.verUbicacionDePlantines(nombrePlantin);
        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (Object[] fila : resultado){
            Map<String, Object> mapAux = new HashMap<>();

            mapAux.put("nombre", fila[0]);
            mapAux.put("stock", fila[1]);
            mapAux.put("tamaño", fila[2]);
            mapAux.put("areaCultivo", fila[3]);

            respuesta.add(mapAux);
        }
        return respuesta;
    }

    public Optional<Plantin> buscarPorId(Integer id) {

        return plantinRepositorio.findById(id);
    }

    public Plantin guardar(Plantin plantin) {

        return plantinRepositorio.save(plantin);
    }

    public void eliminar(Integer id) {

        plantinRepositorio.deleteById(id);
    }
}
