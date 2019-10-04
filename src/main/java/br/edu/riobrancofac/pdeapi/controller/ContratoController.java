package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import br.edu.riobrancofac.pdeapi.repository.ContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "contratos")
public class ContratoController {

    @Autowired
    private ContratosRepository repository;

    @GetMapping(value = "listar")
    public List<Contrato> listarContratos() {
        return repository.findAll();
    }

    @PostMapping(value = "adicionar")
    public Contrato adicionarPrestador(@RequestBody Contrato contrato) {

        return repository.save(contrato);
    }

    @DeleteMapping(value = "excluir/{id}")
    public Contrato deletar(@PathVariable Integer id) {
        Contrato contratoDeletado = repository.getOne(id);
        repository.deleteById(id);

        return contratoDeletado;
    }
}
