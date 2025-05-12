package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.modelo.TipoMovimiento;
import com.Vivero.Canavalia.repositorio.TipoMovimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoServicio {

    @Autowired
    private TipoMovimientoRepositorio tipoMovimientoRepositorio;

    public List<TipoMovimiento> listarTodosTipoMovimiento (){
        return tipoMovimientoRepositorio.findAll();
    }

    public TipoMovimiento guardarTipoMovimiento(TipoMovimiento nuevo){

        return tipoMovimientoRepositorio.save(nuevo);
    }

    public void eliminarTipoMovimiento(Integer id){

        tipoMovimientoRepositorio.deleteById(id);
    }

    public Optional<TipoMovimiento> buscarPorId(Integer id) {

        return tipoMovimientoRepositorio.findById(id);
    }

}
