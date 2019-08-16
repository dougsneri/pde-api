package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pde.entity.Contratante;
import br.edu.riobrancofac.pde.repository.ContratantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "contratantes")
public class ContratanteController {

    @Autowired
    private ContratantesRepository repository;

    @GetMapping(value = "listar")
    public List<Contratante> listarContratantes() {
        return repository.findAll();
    }

    @PostMapping(value = "salvar")
    public Contratante salvarContratante(@RequestBody Contratante contratante) {

        return repository.save(contratante);
    }

    @DeleteMapping(value = "excluir/{cpf}")
    public Contratante deletar(@PathVariable Integer id) {
        Contratante contratanteDeletado = repository.getOne(id);
        repository.deleteById(id);

        return contratanteDeletado;
    }
}
