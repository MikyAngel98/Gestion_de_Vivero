package com.Vivero.Canavalia.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

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

    @OneToMany(mappedBy = "areaCultivo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PlantinAreaCultivo> plantinAreas;
}
