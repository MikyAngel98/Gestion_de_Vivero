package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.PlantinAreaCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantinAreaCultivoRepositorio extends JpaRepository<PlantinAreaCultivo, Integer> {

    //agregar mas metodos si es necesario

}
