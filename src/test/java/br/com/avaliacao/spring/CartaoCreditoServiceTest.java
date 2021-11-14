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

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.services.AlunoService;
import br.com.avaliacao.spring.services.CartaoCreditoService;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
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
		assertThat(cartaoService.findAll().size()).isBetween(3, 4);
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
		List<CartaoCredito> cartoes = cartaoService.findByNome("Emerson Dias de Oliveira");
		CartaoCredito cartao = cartoes.get(0);
		assertThat(cartao.getId()).isEqualTo(1);
		assertThat(cartao.getAluno().getId()).isEqualTo(10930);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("123");
		assertThat(cartao.getFaturas().size()).isEqualTo(3);
		assertThat(cartao.getLimite()).isEqualTo("10000.00");
		assertThat(cartao.getNome()).isEqualTo("Emerson Dias de Oliveira");
		assertThat(cartao.getNumero()).isEqualTo("1234567891234567");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2023, 2));
	}

	@Test
	@DisplayName("Deve buscar um cartao pelo id")
	public void deveBuscarUmCartaoPeloAlunoID()
	{
		List<CartaoCredito> cartoes = cartaoService.findByAlunoId(10930L);
		CartaoCredito cartao = cartoes.get(0);
		cartoes.get(0).getFaturas().size();
		assertThat(cartao.getId()).isEqualTo(1);
		assertThat(cartao.getAluno().getId()).isEqualTo(10930);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("123");
		assertThat(cartao.getFaturas().size()).isEqualTo(3);
		assertThat(cartao.getLimite()).isEqualTo("10000.00");
		assertThat(cartao.getNome()).isEqualTo("Emerson Dias de Oliveira");
		assertThat(cartao.getNumero()).isEqualTo("1234567891234567");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2023, 2));
	}
	
	@Test
	@DisplayName("Deve buscar um cartao pelo id")
	public void deveBuscarUmCartao()
	{
		CartaoCredito cartao = cartaoService.find(1L);
		assertThat(cartao.getId()).isEqualTo(1);
		assertThat(cartao.getAluno().getId()).isEqualTo(10930);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("123");
		assertThat(cartao.getFaturas().size()).isEqualTo(3);
		assertThat(cartao.getLimite()).isEqualTo("10000.00");
		assertThat(cartao.getNome()).isEqualTo("Emerson Dias de Oliveira");
		assertThat(cartao.getNumero()).isEqualTo("1234567891234567");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2023, 2));
	}
	
	@Test
	@DisplayName("Deve buscar um cartao pelo numero")
	public void deveBuscarUmCartaoPeloNumero()
	{
		CartaoCredito cartao = cartaoService.findByNumero("1234567891234567");
		assertThat(cartao.getId()).isEqualTo(1);
		assertThat(cartao.getAluno().getId()).isEqualTo(10930);
		assertThat(cartao.getCodigoSeguranca()).isEqualTo("123");
		assertThat(cartao.getFaturas().size()).isEqualTo(3);
		assertThat(cartao.getLimite()).isEqualTo("10000.00");
		assertThat(cartao.getNome()).isEqualTo("Emerson Dias de Oliveira");
		assertThat(cartao.getNumero()).isEqualTo("1234567891234567");
		assertThat(cartao.getVencimento()).isEqualTo(YearMonth.of(2023, 2));
	}

	@Test
	@DisplayName("Deve atualizar cartao")
	public void deveAtualizarCartao() {
		CartaoCredito cartao = cartaoService.find(2L);
		cartao.setNome("Novo Nome");
		cartaoService.update(cartao);
		assertThat(cartaoService.find(2L).getNome()).isEqualTo("Novo Nome");
	
	}
	
	@Test
	@DisplayName("Deve inserir cartao")
	public void deveInserirAluno() {
		Aluno aluno = alunoService.find(1L);

		CartaoCredito cartao = new CartaoCredito(aluno,"1234", "nome do aluno",YearMonth.of(2021,2),new BigDecimal(2000),"1234",02,true);

		CartaoCredito insert = cartaoService.insert(cartao);
		assertThat(insert.getId()).isEqualTo(3L);
		assertThat(insert.getAluno().getId()).isEqualTo(1);
		assertThat(insert.getCodigoSeguranca()).isEqualTo(cartao.getCodigoSeguranca());
		assertThat(insert.getFaturas().size()).isEqualTo(0);
		assertThat(insert.getLimite()).isEqualTo(cartao.getLimite());
		assertThat(insert.getNome()).isEqualTo(cartao.getNome());
		assertThat(insert.getNumero()).isEqualTo(cartao.getNumero());
		assertThat(insert.getVencimento()).isEqualTo(cartao.getVencimento());
	}
	
	@Test
	@DisplayName("Nao deve excluir cartao devido a relacionament")
	public void naoDeveExcluirAluno() {
		Assertions.assertThrows(DataIntegrityException.class, () -> {
			cartaoService.delete(1L);
		});
	}
}
