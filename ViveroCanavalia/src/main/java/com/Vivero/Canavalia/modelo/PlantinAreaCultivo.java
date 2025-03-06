package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PlantinAreaCultivo")
@Data
public class PlantinAreaCultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plantin_id", nullable = false)
    private Plantin plantin;

    @ManyToOne
    @JoinColumn(name = "area_cultivo_id", nullable = false)
    private AreaCultivo areaCultivo;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(length = 50)
    private String tamaño;

}
