package com.Vivero.Canavalia.controlador;


import com.Vivero.Canavalia.modelo.AreaCultivo;
import com.Vivero.Canavalia.servicios.AreaCultivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/areaCultivo")
public class AreaCultivoControlador {

    @Autowired
    private AreaCultivoServicio areaCultivoServicio;

    //Listar todos las areas de cultivo
    @GetMapping
    public ResponseEntity<List<AreaCultivo>> listarAreasDeCultivo(){

        return ResponseEntity.ok(areaCultivoServicio.listarAreasDeCultivo());
    }

    //guardar areas de cultivo
    @PostMapping
    public ResponseEntity<?> guardarAreaCultivo (@RequestBody AreaCultivo areaCultivo){
        areaCultivoServicio.guardarAreaCultivo(areaCultivo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(areaCultivo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //ver que plantines contiene el area de cultivo
    @GetMapping("detalle/{id}")
    public ResponseEntity<List<Map<String, Object>>> verUbicacionPlantin(@PathVariable Integer id){

        List<Map<String, Object>> resultado = areaCultivoServicio.verPlantinDeAreaCultivo(id);

        if (resultado.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultado);
    }


    //actualizar areas de cultivo
    @PutMapping("/{id}")
    public ResponseEntity<AreaCultivo> actualizarAreaCultivo (@PathVariable Integer id, @RequestBody AreaCultivo areaCultivo){

        if (!areaCultivoServicio.buscarAreaCultivoPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        areaCultivo.setId(id);
        return ResponseEntity.ok(areaCultivoServicio.guardarAreaCultivo(areaCultivo));
    }

    //eliminar area de cultivo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAreaDeCultivo (@PathVariable Integer id){

        if (!areaCultivoServicio.buscarAreaCultivoPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        areaCultivoServicio.eliminarAreaCultivo(id);
        return ResponseEntity.noContent().build();
    }
}
