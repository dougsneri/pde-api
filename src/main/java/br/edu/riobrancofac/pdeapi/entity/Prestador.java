package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    @Column(name = "cpf")
    @JsonProperty("cpf")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres sendo destes 11 apenas números.")
    @NotNull
    private String cpf;

    @Column(name = "nome")
    @JsonProperty("nome")
    @NotNull
    private String nome;

    @Column(name = "sobrenome")
    @JsonProperty("sobrenome")
    @NotNull
    private String sobrenome;

    @OneToMany(mappedBy = "prestador", targetEntity = Contrato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contrato> contratos;

    @Column(name = "data_nascimento")
    @JsonProperty("data_nascimento")
    @NotNull
    @Past
    private Date dataNascimento;
    @Column(name = "ddd1")
    @JsonProperty("ddd1")
    @Size(min = 2, max = 2, message = "DDD inválido, informe um DDD com apenas dois digitos.")
    @NotNull
    private String ddd1;
    @Column(name = "telefone1")
    @JsonProperty("telefone1")
    @Size(min = 8, max = 9, message = "Telefone inválido, informe um telefone com 8 digitos ou 9 para celular.")
    @NotNull
    private String telefone1;
    @Column(name = "ddd2")
    @JsonProperty("ddd2")
    @Size(min = 2, max = 2, message = "DDD inválido, informe um DDD com apenas dois digitos.")
    private String ddd2;
    @Column(name = "telefone2")
    @JsonProperty("telefone2")
    @Size(min = 8, max = 9, message = "Telefone inválido, informe um telefone com 8 digitos ou 9 para celular.")
    private String telefone2;
    @Column(name = "email")
    @JsonProperty("email")
    @Email(message = "Email inválido, informe um e-mail válido")
    @NotNull
    private String email;
    @Column(name = "status_prestador")
    @JsonProperty("status_prestador")
    @NotNull
    private Boolean statusPrestador;

    @Column(name = "usuario")
    @JsonProperty("usuario")
    @NotNull
    private String usuario;
    @Column(name = "senha")
    @JsonProperty("senha")
    @NotNull
    private String senha;

    @Column(name = "cep")
    @JsonProperty("cep")
    @Size(min = 8, max = 8, message = "Cep inválido, informe um cep com 8 digitos separados por hífen ou não.")
    @NotNull
    private String cep;
    @Column(name = "rua")
    @JsonProperty("rua")
    @NotNull
    private String rua;
    @Column(name = "numero")
    @JsonProperty("numero")
    @NotNull
    private String numero;
    @Column(name = "complemento")
    @JsonProperty("complemento")
    private String complemento;
    @Column(name = "bairro")
    @JsonProperty("bairro")
    @NotNull
    private String bairro;
    @Column(name = "cidade")
    @JsonProperty("cidade")
    @NotNull
    private String cidade;
    @Column(name = "estado")
    @JsonProperty("estado")
    @NotNull
    private String estado;

    public void setCpf(String cpf) {
        this.cpf = removeFormatacao(cpf);
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = removeFormatacao(ddd1);
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = removeFormatacao(telefone1);
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = removeFormatacao(ddd2);
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = removeFormatacao(telefone2);
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toLowerCase();
    }

    public void setCep(String cep) {
        this.cep = removeFormatacao(cep);
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.toLowerCase();
    }
}
