package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.AreaCultivo;
import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.modelo.PlantinAreaCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantinAreaCultivoRepositorio extends JpaRepository<PlantinAreaCultivo, Integer> {

    Optional<PlantinAreaCultivo> findByPlantinAndAreaCultivoAndTamaño(Plantin plantin, AreaCultivo areaCultivo, String tamaño);

    //agregar mas metodos si es necesario

}
