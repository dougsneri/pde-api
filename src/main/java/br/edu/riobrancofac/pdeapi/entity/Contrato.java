package br.edu.riobrancofac.pdeapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Prestador prestador;

    @ManyToOne(fetch = FetchType.EAGER)
    private Contratante contratante;

}
