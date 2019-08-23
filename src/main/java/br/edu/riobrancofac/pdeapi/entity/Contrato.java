package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contratos")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contrato", nullable=false)
    @JsonProperty("id_contrato")
    private Integer idContrato;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable=false)
    @JsonProperty("prestador")
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "contratante_id", nullable=false)
    @JsonProperty("contratante")
    private Contratante contratante;

}
