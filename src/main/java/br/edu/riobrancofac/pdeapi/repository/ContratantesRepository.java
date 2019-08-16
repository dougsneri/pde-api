package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pde.entity.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratantesRepository extends JpaRepository<Contratante, Integer> {

    Contratante findByCpf(String cpf);

}
