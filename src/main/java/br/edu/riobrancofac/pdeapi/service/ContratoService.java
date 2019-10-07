package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import br.edu.riobrancofac.pdeapi.repository.ContratosRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoService {

    private final ContratosRepository repository;

    @Autowired
    public ContratoService(final ContratosRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Response<Contrato>> adicionarContrato(Contrato contrato, BindingResult result) {
        Response<Contrato> contratoResponse = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratoResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(contratoResponse);
        }

        contratoResponse.setData(repository.save(contrato));
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoResponse);
    }

    public ResponseEntity<List<Contrato>> listarContratos() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<List<Contrato>> listarContratosEntreDatas(List<String> listaDeDatasInicioEFim) {
        String dataInicio = listaDeDatasInicioEFim.get(0);
        String dataFim = listaDeDatasInicioEFim.get(1);
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByDataServicoBetween(LocalDate.parse(dataInicio), LocalDate.parse(dataFim)));
    }
}
