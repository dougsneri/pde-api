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
    private Integer id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @OneToMany(mappedBy = "contratantes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contrato> contratos;

}
