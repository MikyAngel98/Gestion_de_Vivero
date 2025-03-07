package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.HistorialMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialMovimientoRepositorio extends JpaRepository<HistorialMovimiento, Integer> {

    //Agregar mas Metodos si es necesario
}
