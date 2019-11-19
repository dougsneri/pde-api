package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.dto.ContratoDTO;
import br.edu.riobrancofac.pdeapi.entity.Contrato;
import br.edu.riobrancofac.pdeapi.repository.ContratantesRepository;
import br.edu.riobrancofac.pdeapi.repository.ContratosRepository;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static br.edu.riobrancofac.pdeapi.service.utils.Complemento.getHttpHeaders;

@Service
public class ContratoService {

    private final ContratosRepository repositoryContratos;
    private final PrestadoresRepository repositoryPrestadores;
    private final ContratantesRepository repositoryContratantes;

    @Autowired
    public ContratoService(final ContratosRepository repositoryContratos, final PrestadoresRepository prestadoresRepository, final ContratantesRepository contratantesRepository) {
        this.repositoryContratos = repositoryContratos;
        this.repositoryPrestadores = prestadoresRepository;
        this.repositoryContratantes = contratantesRepository;
    }

    public ResponseEntity<Response<Contrato>> adicionarContrato(ContratoDTO contratoDTO, BindingResult result) {
        Response<Contrato> contratoResponse = new Response<>();

        Contrato contrato = new Contrato();
        contrato.setDataServico(contratoDTO.getContrato().getDataServico());
        contrato.setServicoPrestado(contratoDTO.getContrato().getServicoPrestado());
        contrato.setContratante(repositoryContratantes.findByCpf(contratoDTO.getCpfContratante()));
        contrato.setPrestador(repositoryPrestadores.findByCpf(contratoDTO.getCpfPrestador()));

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratoResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(contratoResponse);
        }

        contratoResponse.setData(repositoryContratos.save(contrato));
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoResponse);
    }

    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> contratos = repositoryContratos.findAll();
        for(Contrato contrato : contratos) {
            contrato.getContratante().setStatusContratante(null);
            contrato.getContratante().setDataNascimento(null);
            contrato.getContratante().setDdd1(null);
            contrato.getContratante().setDdd2(null);
            contrato.getContratante().setTelefone1(null);
            contrato.getContratante().setTelefone2(null);
            contrato.getContratante().setEmail(null);
            contrato.getContratante().setGenero(null);
            contrato.getContratante().setSenha(null);
            contrato.getContratante().setEndereco(null);
            contrato.getContratante().setIdContratante(null);

            contrato.getPrestador().setStatusPrestador(null);
            contrato.getPrestador().setDataNascimento(null);
            contrato.getPrestador().setDdd1(null);
            contrato.getPrestador().setDdd2(null);
            contrato.getPrestador().setTelefone1(null);
            contrato.getPrestador().setTelefone2(null);
            contrato.getPrestador().setEmail(null);
            contrato.getPrestador().setGenero(null);
            contrato.getPrestador().setSenha(null);
            contrato.getPrestador().setEndereco(null);
            contrato.getPrestador().setIdPrestador(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(contratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosEntreDatas(List<String> listaDeDatasInicioEFim) {
        String dataInicio = listaDeDatasInicioEFim.get(0);
        String dataFim = listaDeDatasInicioEFim.get(1);
        return ResponseEntity.status(HttpStatus.OK).body(repositoryContratos.findByDataServicoBetween(LocalDate.parse(dataInicio), LocalDate.parse(dataFim)));
    }
}
