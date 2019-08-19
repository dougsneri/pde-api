package br.edu.riobrancofac.pdeapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "contratantes")
public class Contratante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contratante")
    private Integer idContratante;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contratante")
    private List<Contrato> contratos;

}
