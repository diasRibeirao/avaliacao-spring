package br.com.avaliacao.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.spring.domain.CartaoCredito;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {

	@Transactional(readOnly = true)
	Optional<CartaoCredito> findByNumero(String numero);

	
	@Transactional(readOnly = true)
	List<CartaoCredito> findByNome(String nome);
}
