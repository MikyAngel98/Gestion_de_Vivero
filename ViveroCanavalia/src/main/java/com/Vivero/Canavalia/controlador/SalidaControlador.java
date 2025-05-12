package com.Vivero.Canavalia.controlador;

import com.Vivero.Canavalia.dto.SalidaDTO;
import com.Vivero.Canavalia.servicios.SalidaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salidas")
public class SalidaControlador {

        @Autowired
        private SalidaServicio salidaService;

        @PostMapping("/Registrar")
        public ResponseEntity<?> registrarSalida(@RequestBody SalidaDTO salidaDTO) {
            try {
                salidaService.registrarSalida(salidaDTO);
                return ResponseEntity.ok("Salida registrada con éxito.");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al registrar la salida: " + e.getMessage());
            }
        }

}
