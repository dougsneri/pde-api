package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "contratantes")
public class Contratante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contratante", nullable=false)
    @JsonProperty("id_contratante")
    private Integer idContratante;

    @Column(name = "cpf", nullable=false)
    @JsonProperty("cpf")
    private String cpf;

    @Column(name = "nome")
    @JsonProperty("nome")
    private String nome;

    @Column(name = "sobrenome", nullable=false)
    @JsonProperty("sobrenome")
    private String sobrenome;

    @OneToMany(mappedBy = "contratante", targetEntity = Contrato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contrato> contratos;

}
