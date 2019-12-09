package br.edu.riobrancofac.pdeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contratos")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contrato", nullable = false)
    @JsonProperty("id_contrato")
    private Integer idContrato;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    @JsonProperty("prestador")
    @NotNull
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "contratante_id")
    @JsonProperty("contratante")
    @NotNull
    private Contratante contratante;

    @Column(name = "servico_prestado")
    @JsonProperty("servico_prestado")
    @NotNull(message = "A descrição do serviço prestado não pode ser nula.")
    private String servicoPrestado;

    @Column(name = "data_servico")
    @JsonProperty("data_servico")
    @NotNull(message = "Data da realização do serviço não pode ser nula.")
    private LocalDate dataServico;

    @Column(name = "data_cadastro_contrato")
    @JsonProperty("data_cadastro_contrato")
    @NotNull
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDate dataCadastroContrato = LocalDate.now();
    
    @Column(name = "valor_do_servico")
    @JsonProperty("valor_do_servico")
    @NotNull(message = "Valor do serviço não pode ser nula.")
    private BigDecimal valorDoServico;
}
