package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Prestador;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestadorService {

    @Autowired
    PrestadoresRepository repository;

    public Prestador atualizarPrestador(Prestador prestador) {

        return repository.save(prestador);
    }

    public List<Prestador> listarPrestadores() {

        return repository.findAll();
    }

    public Prestador pesquisarCpfPrestador(String cpf){
        Prestador prestador = repository.findByCpf(cpf);

        return repository.getOne(prestador.getIdPrestador());
    }

    public Prestador excluirCpfPrestador(String cpf){
        Prestador prestadorDeletado = pesquisarCpfPrestador(cpf);
        repository.deleteById(prestadorDeletado.getIdPrestador());

        return prestadorDeletado;
    }
}
