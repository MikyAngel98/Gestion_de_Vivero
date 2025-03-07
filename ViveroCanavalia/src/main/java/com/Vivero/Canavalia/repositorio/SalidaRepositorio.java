package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepositorio extends JpaRepository<Salida, Integer> {

    //agregar mas metodos si es necesario

}
