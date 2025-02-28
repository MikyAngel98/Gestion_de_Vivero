package com.Vivero.Canavalia.servicios;

import com.Vivero.Canavalia.modelo.Plantin;
import com.Vivero.Canavalia.repositorio.PlantinRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlantinServicios {
    @Autowired
    private PlantinRepositorio plantinRepositorio;

    public List<Plantin> listarTodos() {
        return plantinRepositorio.findAll();
    }

    public Optional<Plantin> buscarPorId(Integer id) {
        return plantinRepositorio.findById(id);
    }

    public Plantin guardar(Plantin plantin) {
        return plantinRepositorio.save(plantin);
    }

    public void eliminar(Integer id) {
        plantinRepositorio.deleteById(id);
    }
}
