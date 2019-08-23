package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pdeapi.entity.Prestador;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import br.edu.riobrancofac.pdeapi.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "prestadores")
public class PrestadorController {

    @Autowired
    private PrestadorService service;

    @GetMapping(value = "listar")
    public List<Prestador> listarPrestadores() {
        return service.listarPrestadores();
    }

    @GetMapping(value = "pesquisar/por-cpf/{cpf}")
    public Prestador pesquisarPrestador(@PathVariable String cpf) {

        return service.pesquisarCpfPrestador(cpf);
    }

    @PostMapping(value = "atualizar")
    public Prestador salvarPrestador(@RequestBody Prestador prestador) {

        return service.atualizarPrestador(prestador);
    }

    @DeleteMapping(value = "excluir/por-cpf/{cpf}")
    public Prestador excluirCpfPrestador(@PathVariable String cpf) {

        return service.excluirCpfPrestador(cpf);
    }
}




