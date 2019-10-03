package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "contratantes")
public class Contratante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contratante", nullable = false)
    @JsonProperty("id_contratante")
    private Integer idContratante;

    @Column(name = "cpf")
    @JsonProperty("cpf")
    @NotNull(message = "cpf não pode ser nulo")
    private String cpf;

    @Column(name = "nome")
    @JsonProperty("nome")
    @NotNull(message = "nome não pode ser nulo")
    private String nome;

    @Column(name = "sobrenome")
    @JsonProperty("sobrenome")
    @NotNull(message = "sobrenome não pode ser nulo")
    private String sobrenome;

    @OneToMany(mappedBy = "contratante", targetEntity = Contrato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contrato> contratos;

}
