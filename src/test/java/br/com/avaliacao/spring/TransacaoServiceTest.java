package br.com.avaliacao.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.services.TransacaoService;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class TransacaoServiceTest {

	@Autowired
	private TransacaoService transacaoService;
//	
//	@Test
//	@DisplayName("Deve retornar todos as transacaos")
//	public void deveRetornarTodosOsFaturas() {
//		assertThat(transacaoService.findAll().size()).isEqualTo(40);
//	}
	
//	@Test
//	@DisplayName("Deve apresentar exception ao buscar transacao inexistente")
//	public void deveRetornarErroAoBuscarFaturaInexistente() {
//		Assertions.assertThrows(ObjectNotFoundException.class, ()  -> {
//			transacaoService.find(41L);
//		});
//	}

	@Test
	@DisplayName("Deve atualizar transacao")
	public void deveAtualizarFatura() {
		Transacao transacao = transacaoService.find(10L);
		transacao.setValor(new BigDecimal(10000.01));
		
		transacaoService.update(transacao);
		assertThat(transacaoService.find(10L).getValor()).isEqualTo(new BigDecimal(10000.01).setScale(2,RoundingMode.HALF_EVEN));
	}
	

}
