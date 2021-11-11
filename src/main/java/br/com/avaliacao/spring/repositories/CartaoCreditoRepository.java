package br.com.avaliacao.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.spring.domain.CartaoCredito;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {

	@Transactional(readOnly = true)
	Optional<CartaoCredito> findByNumero(String numero);

	
	@Transactional(readOnly = true)
	List<CartaoCredito> findByNome(String nome);

	@Transactional(readOnly = true)	
	//@Query("SELECT obj FROM CartaoCredito obj INNER JOIN obj.aluno a WHERE a.id = :alunoId ORDER BY obj.id")
	@Query("SELECT obj FROM Aluno a INNER JOIN a.cartoes obj WHERE a.id = :alunoId ORDER BY obj.id")
	List<CartaoCredito> findByAlunoId(@Param("alunoId") Long alunoId);
}
