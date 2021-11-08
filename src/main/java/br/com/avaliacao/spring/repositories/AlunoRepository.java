package br.com.avaliacao.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.spring.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Transactional(readOnly = true)
	Aluno findByNome(String nome);

	@Transactional(readOnly = true)
	Aluno findByMatricula(String matricula);
}
