package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import br.edu.riobrancofac.pdeapi.repository.ContratosRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratoResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(contratoResponse);
        }

        contratoResponse.setData(repository.save(contrato));
        return ResponseEntity.ok(contratoResponse);
    }

    public ResponseEntity<List<Contrato>> listarContratos() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}