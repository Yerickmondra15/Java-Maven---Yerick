package com.videoclub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CINTAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cinta {

    @Id
    @Column(name = "CODIGO_CINTA")
    private Integer codigoCinta;

    @Column(name = "ESTANTERIA")
    private String estanteria;

    @Column(name = "ESTANTE")
    private String estante;

    @Column(name = "FILA")
    private String fila;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "AÑO")
    private Integer año;

    @Column(name = "GENERO")
    private String genero;

}
