package br.com.avaliacao.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;
import br.com.avaliacao.spring.services.FaturaService;
import br.com.avaliacao.spring.services.TransacaoService;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class TransacaoServiceTest {

	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private FaturaService faturaService;
	
	@Test
	@DisplayName("Deve retornar todos as transacaos")
	public void deveRetornarTodosOsFaturas() {
		assertThat(transacaoService.findAll().size()).isEqualTo(40);
	}
	
	@Test
	@DisplayName("Deve apresentar exception ao buscar transacao inexistente")
	public void deveRetornarErroAoBuscarFaturaInexistente() {
		Assertions.assertThrows(ObjectNotFoundException.class, ()  -> {
			transacaoService.find(41L);
		});
	}
	
	@Test
	@DisplayName("Deve buscar fatura especifica")
	public void deveBuscarFaturaEspecifica() {
		Transacao transacao = transacaoService.find(1L);
		transacao.getData();
		assertThat(transacao.getData()).isEqualTo(new Date(01/01/2021));
		assertThat(transacao.getDataFormatada()).isEqualTo("2021/01/01");
		assertThat(transacao.getDescricao()).isEqualTo("xpto");
		assertThat(transacao.getFatura().getId()).isEqualTo(1L);
		assertThat(transacao.getId()).isEqualTo(0L);
		assertThat(transacao.getSituacao()).isEqualTo(0);
		assertThat(transacao.getValor()).isEqualTo(1L);
		assertThat(transacao.getValorFormatado()).isEqualTo("cabare");
	}

	@Test
	@DisplayName("Deve criar uma transacao")
	public void deveCriarTransacao() {
		Fatura fatura = faturaService.find(0L);
		@SuppressWarnings("deprecation")
		Transacao transacao = new Transacao(10000L, fatura, "lancamento teste",
				new Date(2021,01,02), new BigDecimal(1000), SituacaoTransacao.ATIVA);
		Transacao newTransacao = transacaoService.insert(transacao);
		assertThat(transacao.getData()).isEqualTo(newTransacao.getData());
		assertThat(transacao.getDataFormatada()).isEqualTo(newTransacao.getDataFormatada());
		assertThat(transacao.getDescricao()).isEqualTo(newTransacao.getDescricao());
		assertThat(transacao.getFatura().getId()).isEqualTo(newTransacao.getFatura().getId());
		assertThat(transacao.getId()).isEqualTo(newTransacao.getId());
		assertThat(transacao.getSituacao()).isEqualTo(newTransacao.getSituacao());
		assertThat(transacao.getValor()).isEqualTo(newTransacao.getValor());
		assertThat(transacao.getValorFormatado()).isEqualTo(newTransacao.getValorFormatado());
	}
	@Test
	@DisplayName("Deve atualizar transacao")
	public void deveAtualizarFatura() {
		Transacao transacao = transacaoService.find(10L);
		transacao.setValor(new BigDecimal(10000.01));
		
		transacaoService.update(transacao);
		assertThat(transacaoService.find(10L).getValor()).isEqualTo(new BigDecimal(10000.01).setScale(2,RoundingMode.HALF_EVEN));
	}
	
	@Test
	@DisplayName("Deve buscar cancelar transacao da fatura ")
	public void deveCancelarTransacao() {
		transacaoService.cancelar(0L);
		assertThat(transacaoService.find(10000L).getSituacao()).isEqualTo(SituacaoTransacao.CANCELADA);
	}
	

}
