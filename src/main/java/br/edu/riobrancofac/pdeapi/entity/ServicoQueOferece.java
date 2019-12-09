package br.edu.riobrancofac.pdeapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicoQueOferece implements Serializable {
	
	private static final long serialVersionUID = 2608768112501386880L;
	
	public ServicoQueOferece(String descricao, BigDecimal valor) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	@Column(name = "descricao")
    @JsonProperty("descricao")
    @NotNull(message = "Descrição do serviço não pode ser nula")
	private String descricao;
	
	@Column(name = "valor")
    @JsonProperty("valor")
    @NotNull(message = "Valor do serviço não pode ser nulo")
	private BigDecimal valor;

}
