package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Prestador;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class PrestadorService {

    private final PrestadoresRepository repository;

    @Autowired
    public PrestadorService(final PrestadoresRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Response<Prestador>> adicionarPrestador(Prestador prestador, BindingResult result) {
        Response<Prestador> prestadorResponse = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        validaPrestadorCadastrado(prestador, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        prestadorResponse.setData(repository.save(prestador));
        return ResponseEntity.ok(prestadorResponse);
    }

    public ResponseEntity<Response<Prestador>> atualizarPrestador(Prestador prestador, BindingResult result) {
        Response<Prestador> prestadorResponse = new Response<>();

        prestador.setIdPrestador(getIdPrestadorCadastrado(prestador, result));

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        validaPrestadorNaoCadastrado(prestador.getCpf(), result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        prestadorResponse.setData(repository.save(prestador));

        return new ResponseEntity<>(prestadorResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Prestador>> listarPrestadores() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Prestador> pesquisarCpfPrestador(String cpf) {
        Prestador prestador = repository.findByCpf(cpf);

        return new ResponseEntity<>(repository.getOne(prestador.getIdPrestador()), HttpStatus.OK);
    }

    public ResponseEntity<Response<Prestador>> desativarPrestador(String cpf) {
        Response<Prestador> prestadorResponse = new Response<>();

        Prestador prestadorParaDesativar = repository.findByCpf(cpf);

        if (prestadorParaDesativar == null) {
            prestadorResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        if (prestadorParaDesativar.getStatusPrestador().equals(Boolean.FALSE)) {
            prestadorResponse.getErrors().add("Este CPF já foi desativado.");
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        prestadorParaDesativar.setStatusPrestador(Boolean.FALSE);

        repository.save(prestadorParaDesativar);

        prestadorResponse.setData(prestadorParaDesativar);

        return new ResponseEntity<>(prestadorResponse, HttpStatus.ACCEPTED);

    }

    private Integer getIdPrestadorCadastrado(Prestador prestador, BindingResult result) {
        Prestador prestadorTemp = repository.findByCpf(prestador.getCpf());
        if ((prestadorTemp == null) || prestadorTemp.getCpf() == null) {
            validaPrestadorNaoCadastrado(prestador.getCpf(), result);
            return null;
        }
        return prestadorTemp.getIdPrestador();
    }

    private void validaPrestadorCadastrado(Prestador prestador, BindingResult result) {
        Prestador prestadorJaCadastrado = repository.findByCpf(prestador.getCpf());
        if (!(prestadorJaCadastrado == null)) {
            result.addError(new ObjectError("prestador", "Este CPF já está cadastrado."));
        }
    }

    private void validaPrestadorNaoCadastrado(String cpf, BindingResult result) {
        Prestador prestadorJaCadastrado = repository.findByCpf(cpf);
        if (prestadorJaCadastrado == null || prestadorJaCadastrado.getCpf() == null) {
            result.addError(new ObjectError("prestador", "Este CPF não existe em nossa base de dados."));
        }
    }
}
