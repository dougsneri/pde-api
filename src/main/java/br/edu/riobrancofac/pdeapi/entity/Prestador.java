package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static br.edu.riobrancofac.pdeapi.utils.FormataDados.removeFormatacao;

@Data
@Entity
@Table(name = "prestadores")
public class Prestador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prestador", nullable = false)
    @JsonProperty("id_prestador")
    private Integer idPrestador;

    @Column(name = "cpf", nullable = false)
    @JsonProperty("cpf")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres sendo destes 11 apenas números")
    private String cpf;

    @Column(name = "nome", nullable = false)
    @JsonProperty("nome")
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    @JsonProperty("sobrenome")
    private String sobrenome;

    @OneToMany(mappedBy = "prestador", targetEntity = Contrato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contrato> contratos;

    private Date dataNascimento;
    private String ddd1;
    private String telefone1;
    private String ddd2;
    private String telefone2;
    private String email;
    private Boolean statusPrestador;

    private String usuario;
    private String senha;

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public void setCpf(String cpf) {
        this.cpf = removeFormatacao(cpf);
    }
}
