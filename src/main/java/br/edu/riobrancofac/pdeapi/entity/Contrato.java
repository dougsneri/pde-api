package br.edu.riobrancofac.pdeapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contrato")
    private Integer idContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestador")
    private Prestador prestador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contratante")
    private Contratante contratante;

}
