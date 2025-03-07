package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.AreaCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCultivoRepositorio extends JpaRepository <AreaCultivo, Integer>{

    //agregar mas metodos si es necesario
}
