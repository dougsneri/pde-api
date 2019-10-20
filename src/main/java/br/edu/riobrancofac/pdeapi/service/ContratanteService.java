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

import static br.edu.riobrancofac.pdeapi.service.utils.Complemento.getHttpHeaders;

@Service
public class ContratanteService {

    private final ContratantesRepository repository;

    @Autowired
    public ContratanteService(final ContratantesRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Response<Contratante>> adicionarContratante(Contratante contratante, BindingResult result) {
        Response<Contratante> contratanteResponse = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratanteResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        validaContratanteCadastrado(contratante, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> contratanteResponse.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        contratanteResponse.setData(repository.save(contratante));
        return ResponseEntity.status(HttpStatus.CREATED).headers(getHttpHeaders()).body(contratanteResponse);
    }

    public ResponseEntity<List<Contratante>> listarContratantes() {
        return ResponseEntity.status(HttpStatus.OK).headers(getHttpHeaders()).body(repository.findAll());
    }

    public ResponseEntity<Response<Contratante>> pesquisarCpfContratante(String cpf) {
        Response<Contratante> contratanteResponse = new Response<>();
        Contratante contratante = repository.findByCpf(cpf);

        if (contratante == null) {
            contratanteResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }
        contratanteResponse.setData(repository.getOne(contratante.getIdContratante()));
        return ResponseEntity.status(HttpStatus.OK).headers(getHttpHeaders()).body(contratanteResponse);
    }

    public ResponseEntity<Response<Contratante>> desativarContratante(String cpf) {
        Response<Contratante> contratanteResponse = new Response<>();
        Contratante contratanteParaDesativar = repository.findByCpf(cpf);

        if (contratanteParaDesativar == null) {
            contratanteResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        if (contratanteParaDesativar.getStatusContratante().equals(Boolean.FALSE)) {
            contratanteResponse.getErrors().add("Este CPF já foi desativado.");
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        contratanteParaDesativar.setStatusContratante(Boolean.FALSE);

        repository.save(contratanteParaDesativar);

        contratanteResponse.setData(contratanteParaDesativar);

        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(getHttpHeaders()).body(contratanteResponse);

    }

    public ResponseEntity<Response<Contratante>> ativarContratante(String cpf) {
        Response<Contratante> contratanteResponse = new Response<>();

        Contratante contratanteParaAtivar = repository.findByCpf(cpf);

        if (contratanteParaAtivar == null) {
            contratanteResponse.getErrors().add("Este CPF não existe em nossa base de dados.");
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        if (contratanteParaAtivar.getStatusContratante().equals(Boolean.TRUE)) {
            contratanteResponse.getErrors().add("Este CPF já está ativo.");
            return ResponseEntity.badRequest().headers(getHttpHeaders()).body(contratanteResponse);
        }

        contratanteParaAtivar.setStatusContratante(Boolean.TRUE);

        repository.save(contratanteParaAtivar);

        contratanteResponse.setData(contratanteParaAtivar);

        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(getHttpHeaders()).body(contratanteResponse);
    }

    private void validaContratanteCadastrado(Contratante contratante, BindingResult result) {
        Contratante cpfContratanteJaCadastrado = repository.findByCpf(contratante.getCpf());
        if (!(cpfContratanteJaCadastrado == null)) {
            result.addError(new ObjectError("contratante", "Este CPF já está cadastrado."));
        }
        Contratante emailContratanteJaCadastrado = repository.findByEmail(contratante.getEmail());
        if (!(emailContratanteJaCadastrado == null)) {
            result.addError(new ObjectError("contratante", "Este e-mail já está cadastrado."));
        }
    }
}
