package com.Vivero.Canavalia.repositorio;

import com.Vivero.Canavalia.modelo.AreaCultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaCultivoRepositorio extends JpaRepository <AreaCultivo, Integer>{

        List<AreaCultivo> findByNombreContaining(String nombre);

        @Query(value = "EXEC sp_verPlantinesEnAreaCultivo :idAreaCultivo", nativeQuery = true)
        List<Object[]> verPlantinesEnAreaCultivo(@Param("idAreaCultivo") Integer idAreaCultivo);
}
