package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pde.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratosRepository extends JpaRepository<Contrato, Integer> {
}
