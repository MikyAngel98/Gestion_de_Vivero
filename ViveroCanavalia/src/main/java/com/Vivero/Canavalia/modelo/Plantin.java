package com.Vivero.Canavalia.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "Plantin")
@Data
public class Plantin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual = 0;

    @OneToMany(mappedBy = "plantin", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PlantinAreaCultivo> plantinAreas;
}

