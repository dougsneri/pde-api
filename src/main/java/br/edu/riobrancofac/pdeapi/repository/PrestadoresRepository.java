package br.edu.riobrancofac.pdeapi.repository;

import br.edu.riobrancofac.pde.entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadoresRepository extends JpaRepository<Prestador, Integer> {

    Prestador findByCpf(String cpf);

}