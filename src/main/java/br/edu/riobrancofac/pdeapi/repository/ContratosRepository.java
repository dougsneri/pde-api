package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pdeapi.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContratosRepository extends JpaRepository<Contrato, Integer> {

    List<Contrato> findByDataServicoBetween(LocalDate dataInicio, LocalDate dataFim);
}
