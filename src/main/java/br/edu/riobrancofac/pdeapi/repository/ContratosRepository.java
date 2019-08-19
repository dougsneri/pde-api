package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratosRepository extends JpaRepository<Contrato, Integer> {
}
