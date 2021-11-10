package br.com.avaliacao.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.spring.domain.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

	
}
