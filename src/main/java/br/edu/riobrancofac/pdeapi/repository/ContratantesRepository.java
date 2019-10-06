package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pdeapi.entity.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratantesRepository extends JpaRepository<Contratante, Integer> {

    Contratante findByCpf(String cpf);

    Contratante findByEmail(String email);

}
