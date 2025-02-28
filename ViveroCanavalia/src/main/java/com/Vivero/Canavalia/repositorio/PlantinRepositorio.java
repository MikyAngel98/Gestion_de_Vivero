package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.Plantin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantinRepositorio extends JpaRepository<Plantin, Integer>{
    //agregar mas metodos si es necesario
}
