package br.edu.riobrancofac.pdeapi.dto;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoDTO {

    @JsonProperty("contrato")
    private Contrato contrato;

    @JsonProperty("cpf_contratante")
    private String cpfContratante;

    @JsonProperty("cpf_prestador")
    private String cpfPrestador;
}
