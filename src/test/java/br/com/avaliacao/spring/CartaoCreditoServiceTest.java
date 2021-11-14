package br.com.avaliacao.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.services.AlunoService;
import br.com.avaliacao.spring.services.CartaoCreditoService;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class CartaoCreditoServiceTest {

	@Autowired
	private CartaoCreditoService cartaoService;
	
	@Autowired
	private AlunoService alunoService;
	@Test
	@DisplayName("Deve retornar todos os cartoes de credito ")
	public void deveRetornarTodosOsCartoes() {
		assertThat(cartaoService.findAll().size()).isEqualTo(10929);
		//verificar o tamanho, é variavel
	}
	
	@Test
	@DisplayName("Deve apresentar exception ao buscar cartao inexistente")
	public void deveRetornarErroAoBuscarCartaoInexistente() {
		Assertions.assertThrows(ObjectNotFoundException.class, ()  -> {
			cartaoService.find(10930L);
		});
	}
	@Test
	@DisplayName("Deve buscar um cartao pelo nome do aluno")
	public void deveBuscarUmCartaoPeloNome()
	{
		List<CartaoCredito> cartoes = cartaoService.findByNome("Lucia");
		CartaoCredito cartao = cartoes.get(0);
		assertThat(cartao.getId()).isEqualTo(0);
		assertThat(cartao.getAluno().getId()).isEqualTo(0);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("xpto");
		assertThat(cartao.getFaturas().size()).isEqualTo(0);
		assertThat(cartao.getLimite()).isEqualTo("xpto");
		assertThat(cartao.getNome()).isEqualTo("nomeee");
		assertThat(cartao.getNumero()).isEqualTo("xpto");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2021, 2));
	}

	@Test
	@DisplayName("Deve buscar um cartao pelo id")
	public void deveBuscarUmCartaoPeloAlunoID()
	{
		List<CartaoCredito> cartoes = cartaoService.findByAlunoId(0L);
		CartaoCredito cartao = cartoes.get(0);
		assertThat(cartao.getId()).isEqualTo(0);
		assertThat(cartao.getAluno().getId()).isEqualTo(0);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("xpto");
		assertThat(cartao.getFaturas().size()).isEqualTo(0);
		assertThat(cartao.getLimite()).isEqualTo("xpto");
		assertThat(cartao.getNome()).isEqualTo("nomeee");
		assertThat(cartao.getNumero()).isEqualTo("xpto");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2021, 2));
	}
	
	@Test
	@DisplayName("Deve buscar um cartao pelo id")
	public void deveBuscarUmCartao()
	{
		CartaoCredito cartao = cartaoService.find(0L);
		assertThat(cartao.getId()).isEqualTo(0);
		assertThat(cartao.getAluno().getId()).isEqualTo(0);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("xpto");
		assertThat(cartao.getFaturas().size()).isEqualTo(0);
		assertThat(cartao.getLimite()).isEqualTo("xpto");
		assertThat(cartao.getNome()).isEqualTo("nomeee");
		assertThat(cartao.getNumero()).isEqualTo("xpto");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2021, 2));
	}
	
	@Test
	@DisplayName("Deve buscar um cartao pelo numero")
	public void deveBuscarUmCartaoPeloNumero()
	{
		CartaoCredito cartao = cartaoService.findByNumero("123443321");
		assertThat(cartao.getId()).isEqualTo(0);
		assertThat(cartao.getAluno().getId()).isEqualTo(0);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("xpto");
		assertThat(cartao.getFaturas().size()).isEqualTo(0);
		assertThat(cartao.getLimite()).isEqualTo("xpto");
		assertThat(cartao.getNome()).isEqualTo("nomeee");
		assertThat(cartao.getNumero()).isEqualTo("xpto");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2021, 2));
	}

	@Test
	@DisplayName("Deve atualizar cartao")
	public void deveAtualizarCartao() {
		CartaoCredito cartao = cartaoService.find(1L);
		cartao.setNome("Novo Nome");
		cartaoService.update(cartao);
		assertThat(cartaoService.find(1L).getNome()).isEqualTo("Novo Nome");
	}
	
	@Test
	@DisplayName("Deve inserir cartao")
	public void deveInserirAluno() {
		Aluno aluno = alunoService.find(1L);

		CartaoCredito cartao = new CartaoCredito(aluno,"1234", "nome do aluno",YearMonth.of(2021,2),new BigDecimal(2000),"1234",02,true);

		CartaoCredito insert = cartaoService.insert(cartao);
		assertThat(cartao.getId()).isEqualTo(0);
		assertThat(cartao.getAluno().getId()).isEqualTo(0);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("xpto");
		assertThat(cartao.getFaturas().size()).isEqualTo(0);
		assertThat(cartao.getLimite()).isEqualTo("xpto");
		assertThat(cartao.getNome()).isEqualTo("nomeee");
		assertThat(cartao.getNumero()).isEqualTo("xpto");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2021, 2));
	}
	
	@Test
	@DisplayName("Nao deve excluir cartao devido a relacionament")
	public void naoDeveExcluirAluno() {
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			cartaoService.delete(0L);
		});
	}
}
