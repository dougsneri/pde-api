package br.edu.riobrancofac.pdeapi.controller;

import br.edu.riobrancofac.pde.entity.Prestador;
import br.edu.riobrancofac.pde.repository.PrestadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "prestadores")
public class PrestadorController {

    @Autowired
    private PrestadoresRepository repository;

    @GetMapping(value = "listar")
    public List<Prestador> listarPrestadores() {
        return repository.findAll();
    }

//    @GetMapping(value = "listar/por-nome")
//    public List<Prestador> pesquisarPrestadores(@RequestParam String nome) {
//        return repository.pesquisarPrestador(nome);
//    }

//    @GetMapping(value = "listar/por-nome-comecando-com")
//    public List<Prestador> porNomeComecandoCom(@RequestParam String nome) {
//        return repository.findByNomeStartingWithIgnoreCase(nome);
//    }

//    @GetMapping(value = "pesquisar/por-cpf/{cpf}")
//    public Prestador pesquisarPrestador(@PathVariable Long cpf) {
//        return repository.getOne(cpf);
//    }

//    @GetMapping(value = "pesquisar/por-nome")
//    public Prestador porNome(@RequestParam String nome) {
//        return repository.findByNome(nome);
//    }

    @PostMapping(value = "salvar")
    public Prestador salvarPrestador(@RequestBody Prestador prestador) {

        return repository.save(prestador);
    }

//    @DeleteMapping(value = "excluir/{cpf}")
//    public Prestador deletar(@PathVariable Long cpf) {
//        Prestador prestadorDeletado = repository.getOne(cpf);
//        repository.deleteById(cpf);
//
//        return prestadorDeletado;
//    }
}




