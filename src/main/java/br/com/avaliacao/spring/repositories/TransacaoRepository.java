package br.com.avaliacao.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacao.spring.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	
}
