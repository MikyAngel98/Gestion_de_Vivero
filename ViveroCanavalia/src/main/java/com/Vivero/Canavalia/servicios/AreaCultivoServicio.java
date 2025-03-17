package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.modelo.AreaCultivo;
import com.Vivero.Canavalia.repositorio.AreaCultivoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AreaCultivoServicio {

    @Autowired
    private AreaCultivoRepositorio areaCultivoRepositorio;

    //Listar todos las areas de cultivo de la Bdd
    public List<AreaCultivo> listarAreasDeCultivo(){
        return areaCultivoRepositorio.findAll();
    }

    //Buscar area de cultivo po id
    public Optional<AreaCultivo> buscarAreaCultivoPorId (Integer id){
        return areaCultivoRepositorio.findById(id);
    }

    //Buscar por letras en el nombre
    public List<AreaCultivo> buscarAreaCultivoPorNombre(String nombre){
        return areaCultivoRepositorio.findByNombreContaining(nombre);
    }

    //Mustra que plantines estan en un area de cultivo
    public List<Map<String, Object>> verPlantinDeAreaCultivo (Integer idAreaCultivo){
        List<Object[]> resultado = areaCultivoRepositorio.verPlantinesEnAreaCultivo(idAreaCultivo);
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

    //guardar nuevo area de cultivo
    public AreaCultivo guardarAreaCultivo (AreaCultivo areaCultivo){
        return areaCultivoRepositorio.save(areaCultivo);
    }

    //Eliminar area de cultivo por id
    public void eliminarAreaCultivo (Integer id){
        areaCultivoRepositorio.deleteById(id);
    }
}
