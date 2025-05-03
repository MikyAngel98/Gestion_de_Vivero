package com.Vivero.Canavalia.controlador;

import com.Vivero.Canavalia.dto.IngresoDTO;
import com.Vivero.Canavalia.servicios.IngresoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingresos")
@RequiredArgsConstructor
public class IngresoControlador {

    private final IngresoServicio ingresoServicio;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarIngreso(@RequestBody IngresoDTO datos) {
        ingresoServicio.registrarIngreso(datos);
        return ResponseEntity.ok("Ingreso registrado exitosamente");
    }
}
