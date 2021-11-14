package br.com.avaliacao.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.services.AlunoService;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class AlunoServiceTest {

	@Autowired
	private AlunoService alunoService;
	
	@Test
	@DisplayName("Deve retornar todos os alunos")
	public void deveRetornarTodosOsAlunos() {
		assertThat(alunoService.findAll().size()).isEqualTo(10929);
	}
	
	@Test
	@DisplayName("Deve apresentar exception ao buscar aluno inexistente")
	public void deveRetornarErroAoBuscarAlunoInexistente() {
		Assertions.assertThrows(ObjectNotFoundException.class, ()  -> {
			alunoService.find(10930L);
		});
	}
//	@Test
//	@DisplayName("Deve buscar um aluno pelo id")
//	public void deveBuscarUmAluno()
//	{
//		Aluno aluno = alunoService.find(0L);
//		assertThat(aluno.getId()).isEqualTo(0);
//		assertThat(aluno.getCartoes().size()).isEqualTo(0);
//		assertThat(aluno.getMatricula()).isEqualTo("matricula");
//		assertThat(aluno.getNome()).isEqualTo("nome");
//	}

	@Test
	@DisplayName("Deve atualizar aluno")
	public void deveAtualizarAluno() {
		Aluno oldAluno = alunoService.find(10929L);
		oldAluno.setNome("Novo Nome");
		alunoService.update(oldAluno);
		assertThat(alunoService.find(10929L).getNome()).isEqualTo("Novo Nome");
	}
//	
//	@Test
//	@DisplayName("Deve inserir aluno")
//	public void deveInserirAluno() {
//		Aluno novoAluno = new Aluno(null, "1234", "Novo aluno");
//		Aluno insert = alunoService.insert(novoAluno);
//		assertThat(insert.getMatricula()).isEqualTo("1234");
//		assertThat(insert.getNome()).isEqualTo("Novo aluno");
//	}
	
	@Test
	@DisplayName("Nao deve excluir aluno devido a relacionament")
	public void naoDeveExcluirAluno() {
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			alunoService.delete(0L);
		});
	}
}
