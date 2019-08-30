package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pdeapi.entity.Prestador;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import br.edu.riobrancofac.pdeapi.service.PrestadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "prestadores")
public class PrestadorController {

    private static final Logger log = LoggerFactory.getLogger(PrestadorController.class);

    @Autowired
    private PrestadorService service;

    @GetMapping(value = "listar")
    public ResponseEntity<List<Prestador>> listarPrestadores() {
        return service.listarPrestadores();
    }

    @GetMapping(value = "pesquisar/por-cpf/{cpf}")
    public ResponseEntity<Prestador> pesquisarPrestador(@PathVariable String cpf) {
        return service.pesquisarCpfPrestador(cpf);
    }

    @PostMapping(value = "adicionar")
    public ResponseEntity<Response<Prestador>> adicionarPrestador(@Valid @RequestBody Prestador prestador, BindingResult result) {

        log.info("Cadastrando Prestador: " + prestador.getNome() + " " + prestador.getSobrenome());

        return service.adicionarPrestador(prestador, result);
    }

    @PostMapping(value = "atualizar")
    public ResponseEntity<Response<Prestador>> atualizarPrestador(@Valid @RequestBody Prestador prestador, BindingResult result) {

        log.info("Atualizando Prestador: " + prestador.getNome() + " " + prestador.getSobrenome());

        return service.atualizarPrestador(prestador, result);
    }

    @DeleteMapping(value = "excluir/por-cpf/{cpf}")
    public ResponseEntity<Prestador> excluirPrestador(@PathVariable String cpf) {
        return service.excluirCpfPrestador(cpf);
    }

}