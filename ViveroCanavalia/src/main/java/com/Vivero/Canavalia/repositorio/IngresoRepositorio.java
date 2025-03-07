package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepositorio extends JpaRepository<Ingreso, Integer> {
}
