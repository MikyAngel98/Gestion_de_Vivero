package com.Vivero.Canavalia.controlador;


import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.modelo.TipoMovimiento;
import com.Vivero.Canavalia.servicios.TipoMovimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tipoMovimiento")
public class TipoMovimientoControlador {

    @Autowired
    private TipoMovimientoServicio tipoMovimientoServicio;

    @GetMapping
    public ResponseEntity<List<TipoMovimiento>> listarTipoMovimiento(){

        return  ResponseEntity.ok(tipoMovimientoServicio.listarTodosTipoMovimiento());
    }

    // Crear un nuevo Tipo de Movimiento
    @PostMapping
    public ResponseEntity<?> crearTipoMovimiento(@RequestBody TipoMovimiento nuevo) {

        tipoMovimientoServicio.guardarTipoMovimiento(nuevo) ;

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nuevo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // Actualizar un tipo de movimiento
    @PutMapping("/{id}")
    public ResponseEntity<TipoMovimiento> actualizarTipoMovimiento(@PathVariable Integer id, @RequestBody TipoMovimiento actualizar) {
        if (!tipoMovimientoServicio.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        actualizar.setId(id);
        return ResponseEntity.ok(tipoMovimientoServicio.guardarTipoMovimiento(actualizar));
    }

    // Eliminar un tipo de movimiento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoMovimiento(@PathVariable Integer id) {
        if (!tipoMovimientoServicio.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoMovimientoServicio.eliminarTipoMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
