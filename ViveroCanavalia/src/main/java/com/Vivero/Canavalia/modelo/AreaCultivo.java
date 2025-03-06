package com.Vivero.Canavalia.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AreaCultivo")
@Data
public class AreaCultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(length = 25)
    private String medida;

}
