package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.Plantin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlantinRepositorio extends JpaRepository<Plantin, Integer>{

    List<Plantin> findByNombreContaining (String nombre);

}
