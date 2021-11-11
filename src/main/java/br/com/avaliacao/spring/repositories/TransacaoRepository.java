package br.com.avaliacao.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.spring.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	@Transactional(readOnly = true)	
	@Query("SELECT obj FROM Fatura a INNER JOIN a.transacoes obj WHERE a.id = :faturaId ORDER BY obj.id")
	List<Transacao> findByFaturaId(@Param("faturaId") Long faturaId);
}
