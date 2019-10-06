package br.edu.riobrancofac.pdeapi.entity;

import br.edu.riobrancofac.pdeapi.enums.Genero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static br.edu.riobrancofac.pdeapi.utils.FormataDados.removeFormatacao;

@Data
@Entity
@Table(name = "prestadores")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @OneToMany(mappedBy = "prestador", targetEntity = Contrato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contrato> contratos;

    @Column(name = "data_nascimento")
    @JsonProperty("data_nascimento")
    @NotNull(message = "Data de nascimento não pode ser nula")
    @Past
    private LocalDate dataNascimento;

    @Column(name = "ddd1")
    @JsonProperty("ddd1")
    @Size(min = 2, max = 2, message = "DDD inválido, informe um DDD com apenas dois digitos.")
    @NotNull(message = "ddd não pode ser nulo")
    private String ddd1;

    @Column(name = "telefone1")
    @JsonProperty("telefone1")
    @Size(min = 8, max = 9, message = "Telefone inválido, informe um telefone com 8 digitos ou 9 para celular.")
    @NotNull(message = "telefone não pode ser nulo")
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
    @NotNull(message = "e-mail não pode ser nulo")
    private String email;

    @Column(name = "status_prestador")
    @JsonProperty("status_prestador")
    @NotNull(message = "Status não pode ser nulo")
    private Boolean statusPrestador = Boolean.TRUE;

    @Column(name = "genero")
    @JsonProperty("genero")
    @NotNull(message = "Gênero não pode ser nulo")
    @Enumerated
    private Genero genero;

    @Column(name = "senha")
    @JsonProperty("senha")
    @NotNull(message = "Senha não pode ser nula")
    private String senha;

    @Column(name = "data_cadastro_prestador")
    @JsonProperty("data_cadastro_prestador")
    @NotNull
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDate dataCadastroPrestador = LocalDate.now();

    @Embedded
    private Endereco endereco;

    public void setCpf(String cpf) {
        this.cpf = removeFormatacao(cpf);
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

}
