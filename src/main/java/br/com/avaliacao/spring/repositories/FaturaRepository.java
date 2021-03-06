package br.com.avaliacao.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.spring.domain.Fatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM CartaoCredito a INNER JOIN a.faturas obj WHERE a.id = :cartaoCreditoId ORDER BY obj.id")
	List<Fatura> findByCartaoCreditoId(@Param("cartaoCreditoId") Long cartaoCreditoId);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM CartaoCredito a INNER JOIN a.faturas obj WHERE a.id = :cartaoCreditoId AND obj.dataPagamento IS NULL ORDER BY obj.id")
	List<Fatura> findFaturasAbertasByCartaoCreditoId(@Param("cartaoCreditoId") Long cartaoCreditoId);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM CartaoCredito a INNER JOIN a.faturas obj WHERE a.id = :cartaoCreditoId AND obj.dataPagamento IS NOT NULL ORDER BY obj.id")
	List<Fatura> findFaturasPagasByCartaoCreditoId(@Param("cartaoCreditoId") Long cartaoCreditoId);

	@Transactional(readOnly = true)
	@Query(value = "SELECT  * FROM FATURA WHERE CARTAO_CREDITO_ID = :cartaoCreditoId AND DATA_FECHAMENTO > NOW() AND DATA_PAGAMENTO IS NULL ORDER BY DATA_FECHAMENTO LIMIT 1", nativeQuery=true)
	Fatura findFaturaAtualByCartaoCreditoId(@Param("cartaoCreditoId") Long cartaoCreditoId);
	
}
