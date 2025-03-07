package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimientoRepositorio extends JpaRepository<TipoMovimiento, Integer> {
    //agregar mas metodos si es necesario
}
