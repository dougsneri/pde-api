package br.edu.riobrancofac.pdeapi.service;

import br.edu.riobrancofac.pdeapi.entity.Contratante;
import br.edu.riobrancofac.pdeapi.repository.ContratantesRepository;
import br.edu.riobrancofac.pdeapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class ContratanteService {

    private final ContratantesRepository repository;

    @Autowired
    public ContratanteService(final ContratantesRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Response<Contratante>> adicionarContratante(Contratante contratante, BindingResult result) {
        Response<Contratante> contratanteResponse = new Response<>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratanteResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        validaContratanteCadastrado(contratante, result);

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratanteResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        contratanteResponse.setData(repository.save(contratante));
        return ResponseEntity.ok(contratanteResponse);
    }

    public ResponseEntity<List<Contratante>> listarContratantes() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Contratante> pesquisarCpfContratante(String cpf) {
        Contratante contratante = repository.findByCpf(cpf);

        return new ResponseEntity<>(repository.getOne(contratante.getIdContratante()), HttpStatus.OK);
    }

    public ResponseEntity<Response<Contratante>> desativarContratante(String cpf) {
        Response<Contratante> contratanteResponse = new Response<>();

        Contratante contratanteParaDesativar = repository.findByCpf(cpf);

        if(contratanteParaDesativar == null) {
            contratanteResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        if(contratanteParaDesativar.getStatusContratante().equals(Boolean.FALSE)) {
            contratanteResponse.getErrors().add("Este CPF já foi desativado.");
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        contratanteParaDesativar.setStatusContratante(Boolean.FALSE);

        repository.save(contratanteParaDesativar);

        contratanteResponse.setData(contratanteParaDesativar);

        return new ResponseEntity<>(contratanteResponse, HttpStatus.ACCEPTED);

    }

    public ResponseEntity<Response<Contratante>> ativarContratante(String cpf) {
        Response<Contratante> contratanteResponse = new Response<>();

        Contratante contratanteParaAtivar = repository.findByCpf(cpf);

        if (contratanteParaAtivar == null) {
            contratanteResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        if (contratanteParaAtivar.getStatusContratante().equals(Boolean.TRUE)) {
            contratanteResponse.getErrors().add("Este CPF já está ativo.");
            return ResponseEntity.badRequest().body(contratanteResponse);
        }

        contratanteParaAtivar.setStatusContratante(Boolean.TRUE);

        repository.save(contratanteParaAtivar);

        contratanteResponse.setData(contratanteParaAtivar);

        return new ResponseEntity<>(contratanteResponse, HttpStatus.ACCEPTED);
    }

    private void validaContratanteCadastrado(Contratante contratante, BindingResult result) {
        Contratante cpfContratanteJaCadastrado = repository.findByCpf(contratante.getCpf());
        if(!(cpfContratanteJaCadastrado == null)) {
            result.addError(new ObjectError("contratante", "Este CPF já está cadastrado."));
        }
        Contratante emailContratanteJaCadastrado = repository.findByEmail(contratante.getEmail());
        if(!(emailContratanteJaCadastrado == null)) {
            result.addError(new ObjectError("contratante", "Este e-mail já está cadastrado."));
        }
    }
}
