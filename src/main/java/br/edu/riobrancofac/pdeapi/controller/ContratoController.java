package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pdeapi.dto.ContratoDTO;
import br.edu.riobrancofac.pdeapi.entity.Contrato;
import br.edu.riobrancofac.pdeapi.repository.ContratosRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import br.edu.riobrancofac.pdeapi.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "contratos")
public class ContratoController {

    private static final Logger log = LoggerFactory.getLogger(ContratoController.class);

    @Autowired
    private ContratoService service;

    @GetMapping(value = "listar/contratos/todos")
    @CrossOrigin
    public ResponseEntity<List<Contrato>> listarContratos() {
        log.info("Listando Todos os Contratos.");
        return service.listarContratos();
    }

    @PostMapping(value = "adicionar")
    @CrossOrigin
    public ResponseEntity<Response<Contrato>> adicionarContrato(@Valid @RequestBody ContratoDTO contratoDTO, BindingResult result) {
        log.info("Adicionando um novo contrato.");
        return service.adicionarContrato(contratoDTO, result);
    }

    @GetMapping(value = "listar/entre-datas")
    @CrossOrigin
    public ResponseEntity<List<Contrato>> listarContratosEntreDatas(@RequestBody List<String> listaDeDatasInicioEFim) {
        log.info("Listando contratos entre datas");
        return service.listarContratosEntreDatas(listaDeDatasInicioEFim);
    }

    @GetMapping(value = "listar/contratos/prestadores-por-cpf/{cpf}")
    @CrossOrigin
    public ResponseEntity<List<Contrato>> listarContratosPrestadoresPorCpf(@PathVariable String cpf) {
        log.info("Listando Contratos do Prestador Sob o cpf : " + cpf + ".");
        return service.listarContratosDePrestadoresPorCpf(cpf);
    }

    @GetMapping(value = "listar/contratos/contratantes-por-cpf/{cpf}")
    @CrossOrigin
    public ResponseEntity<List<Contrato>> listarContratosContratantesPorCpf(@PathVariable String cpf) {
        log.info("Listando Contratos do Contratante Sob o Cpf : " + cpf + ".");
        return service.listarContratosDeContratantesPorCpf(cpf);
    }

}
