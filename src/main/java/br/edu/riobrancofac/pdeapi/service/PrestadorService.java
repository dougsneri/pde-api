package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Prestador;
import br.edu.riobrancofac.pdeapi.repository.PrestadoresRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static br.edu.riobrancofac.pdeapi.service.utils.Complemento.getHttpHeaders;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(prestadorResponse);
    }

    /*public ResponseEntity<Response<Prestador>> atualizarPrestador(Prestador prestador, BindingResult result) {
        Response<Prestador> prestadorResponse = new Response<>();

        prestador.setIdPrestador(getIdPrestadorCadastrado(prestador, result));

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(prestadorResponse);
        }

        validaPrestadorNaoCadastrado(prestador, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> prestadorResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(prestadorResponse);
        }

        prestadorResponse.setData(repository.save(prestador));

        return new ResponseEntity<>(prestadorResponse, HttpStatus.CREATED);
    }*/

    public ResponseEntity<List<Prestador>> listarPrestadores() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<Response<Prestador>> pesquisarCpfPrestador(String cpf) {
        Response<Prestador> prestadorResponse = new Response<>();
        Prestador prestador = repository.findByCpf(cpf);

        if (prestador == null) {
            prestadorResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().body(prestadorResponse);
        }
        prestadorResponse.setData(repository.getOne(prestador.getIdPrestador()));
        return ResponseEntity.status(HttpStatus.OK).body(prestadorResponse);
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

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prestadorResponse);

    }

    public ResponseEntity<Response<Prestador>> ativarPrestador(String cpf) {
        Response<Prestador> prestadorResponse = new Response<>();

        Prestador prestadorParaAtivar = repository.findByCpf(cpf);

        if (prestadorParaAtivar == null) {
            prestadorResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        if (prestadorParaAtivar.getStatusPrestador().equals(Boolean.TRUE)) {
            prestadorResponse.getErrors().add("Este CPF já está ativo.");
            return ResponseEntity.badRequest().body(prestadorResponse);
        }

        prestadorParaAtivar.setStatusPrestador(Boolean.TRUE);

        repository.save(prestadorParaAtivar);

        prestadorResponse.setData(prestadorParaAtivar);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prestadorResponse);
    }

//    private Integer getIdPrestadorCadastrado(Prestador prestador, BindingResult result) {
//        Prestador prestadorTemp = repository.findByCpf(prestador.getCpf());
//        if ((prestadorTemp == null) || prestadorTemp.getCpf() == null) {
//            validaPrestadorNaoCadastrado(prestador.getCpf(), result);
//            return null;
//        }
//        return prestadorTemp.getIdPrestador();
//    }

    private void validaPrestadorCadastrado(Prestador prestador, BindingResult result) {
        Prestador cpfPrestadorJaCadastrado = repository.findByCpf(prestador.getCpf());
        if (!(cpfPrestadorJaCadastrado == null)) {
            result.addError(new ObjectError("prestador", "Este CPF já está cadastrado."));
        }
        Prestador emailPrestadorJaCadastrado = repository.findByEmail(prestador.getEmail());
        if (!(emailPrestadorJaCadastrado == null)) {
            result.addError(new ObjectError("prestador", "Este e-mail já está cadastrado."));
        }
    }

/*    private void validaPrestadorNaoCadastrado(Prestador prestador, BindingResult result) {
        Prestador prestadorJaCadastradoNesteCpf = repository.findByCpf(prestador.getCpf());
        if (prestadorJaCadastradoNesteCpf == null) {
            result.addError(new ObjectError("prestador", "Este CPF não existe em nossa base de dados para atualizarmos."));
        }
        Prestador prestadorJaCadastradoNesteEmail = repository.findByEmail(prestador.getEmail());
        if(!(prestadorJaCadastradoNesteEmail == null)) {
            if(!prestadorJaCadastradoNesteEmail.getEmail().equals(prestador.getEmail())) {
                result.addError(new ObjectError("prestador", "O e-mail que você está tentando atualizar já existe cadastrado em nossa base de dados."));
            }
        }
        if (!(prestadorJaCadastradoNesteCpf == null)) {
            prestador.setIdPrestador(prestadorJaCadastradoNesteCpf.getIdPrestador());
        }
    }*/

}
