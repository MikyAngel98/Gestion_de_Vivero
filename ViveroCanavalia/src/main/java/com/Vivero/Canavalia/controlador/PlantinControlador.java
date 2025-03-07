package com.Vivero.Canavalia.controlador;

import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.servicios.PlantinServicios;

import java.net.URI;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/plantines")
public class PlantinControlador {
     @Autowired
    private PlantinServicios plantinService;

    // Listar todos los plantines
    @GetMapping
    public ResponseEntity<List<Plantin>>  listarTodos() {

        return ResponseEntity.ok(plantinService.listarTodos());
        //return plantinService.listarTodos();
    }

    // Buscar un plantín por ID
    @GetMapping("/{id}")
    public ResponseEntity<Plantin> obtenerPorId(@PathVariable Integer id) {
        Optional<Plantin> plantin = plantinService.buscarPorId(id);
        return plantin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo plantín
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Plantin plantin) {

        plantinService.guardar(plantin);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(plantin.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Buscar plantin por nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPlantinPorNombre(@PathVariable String nombre){

        List<Plantin> resultado = plantinService.buscarPlantinPorNombre(nombre);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron plantines con el nombre " + nombre);
        }
        return ResponseEntity.ok(resultado);
    }

    // Actualizar un plantín
    @PutMapping("/{id}")
    public ResponseEntity<Plantin> actualizar(@PathVariable Integer id, @RequestBody Plantin plantin) {
        if (!plantinService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        plantin.setId(id);
        return ResponseEntity.ok(plantinService.guardar(plantin));
    }

    // Eliminar un plantín
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!plantinService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        plantinService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
