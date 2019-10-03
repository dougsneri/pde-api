package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import static br.edu.riobrancofac.pdeapi.utils.FormataDados.removeFormatacao;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cep")
    @JsonProperty("cep")
    @Size(min = 8, max = 8, message = "Cep inválido, informe um cep com 8 digitos separados por hífen ou não.")
    @NotNull(message = "Cep não pode ser nulo")
    private String cep;

    @Column(name = "rua")
    @JsonProperty("rua")
    @NotNull(message = "Rua não pode ser nulo")
    private String rua;

    @Column(name = "numero")
    @JsonProperty("numero")
    @NotNull(message = "Numero não pode ser nulo")
    private String numero;

    @Column(name = "complemento")
    @JsonProperty("complemento")
    private String complemento;

    @Column(name = "bairro")
    @JsonProperty("bairro")
    @NotNull(message = "Bairro não pode ser nulo")
    private String bairro;

    @Column(name = "cidade")
    @JsonProperty("cidade")
    @NotNull(message = "Cidade não pode ser nula")
    private String cidade;

    @Column(name = "estado")
    @JsonProperty("estado")
    @NotNull(message = "Estado não pode ser nulo")
    private String estado;

    public void setCep(String cep) {
        this.cep = removeFormatacao(cep);
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.toLowerCase();
    }
}
