package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pdeapi.entity.Contratante;
import br.edu.riobrancofac.pdeapi.response.Response;
import br.edu.riobrancofac.pdeapi.service.ContratanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "contratantes")
public class ContratanteController {

    private static final Logger log = LoggerFactory.getLogger(ContratanteController.class);

    @Autowired
    private ContratanteService service;

    @GetMapping(value = "listar")
    public ResponseEntity<List<Contratante>> listarContratantes() {
        log.info("Listando Contratantes.");
        return service.listarContratantes();
    }

    @GetMapping(value = "pesquisar/por-cpf/{cpf}")
    public ResponseEntity<Contratante> pesquisarContratante(@PathVariable String cpf) {
        log.info("Buscando contratante do cpf: " + cpf);
        return service.pesquisarCpfContratante(cpf);
    }

    @PostMapping(value = "adicionar")
    public ResponseEntity<Response<Contratante>> adicionarContratante(@Valid @RequestBody Contratante contratante, BindingResult result) {
        log.info("Cadastrando Contratante: " + contratante.getNome() + " " + contratante.getSobrenome());
        return service.adicionarContratante(contratante, result);
    }

    @PostMapping(value = "desativar/por-cpf/{cpf}")
    public ResponseEntity<Response<Contratante>> desativarContratante(@PathVariable String cpf) {
        log.info("Desativando Contratante do cpf : " + cpf);
        return service.desativarContratante(cpf);
    }

    @PostMapping(value = "ativar/por-cpf/{cpf}")
    public ResponseEntity<Response<Contratante>> ativarContratante(@PathVariable String cpf) {
        log.info("Ativando Contratante do cpf : " + cpf);
        return service.ativarContratante(cpf);
    }

}
