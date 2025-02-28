package com.Vivero.Canavalia.controlador;

import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.servicios.PlantinServicios;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plantines")
public class PlantinControlador {
     @Autowired
    private PlantinServicios plantinService;

    // Listar todos los plantines
    @GetMapping
    public List<Plantin> listarTodos() {
        return plantinService.listarTodos();
    }

    // Buscar un plantín por ID
    @GetMapping("/{id}")
    public ResponseEntity<Plantin> obtenerPorId(@PathVariable Integer id) {
        Optional<Plantin> plantin = plantinService.buscarPorId(id);
        return plantin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo plantín
    @PostMapping
    public Plantin crear(@RequestBody Plantin plantin) {
        return plantinService.guardar(plantin);
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
