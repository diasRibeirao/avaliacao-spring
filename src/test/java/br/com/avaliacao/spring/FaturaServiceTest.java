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
import br.com.avaliacao.spring.services.FaturaService;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class FaturaServiceTest {

	@Autowired
	private FaturaService faturaService;
	
	@Test
	@DisplayName("Deve retornar todos as faturas")
	public void deveRetornarTodosOsFaturas() {
		assertThat(faturaService.findAll().size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Deve apresentar exception ao buscar fatura inexistente")
	public void deveRetornarErroAoBuscarFaturaInexistente() {
		Assertions.assertThrows(ObjectNotFoundException.class, ()  -> {
			faturaService.find(11L);
		});
	}

	@Test
	@DisplayName("Deve atualizar fatura")
	public void deveAtualizarFatura() {
		Fatura fatura = faturaService.find(2L);
		fatura.setValorPago(new BigDecimal(10000.01));
		
		faturaService.update(fatura);
		assertThat(faturaService.find(2L).getValorPago()).isEqualTo(new BigDecimal(10000.01).setScale(2,RoundingMode.HALF_EVEN));
	}
	
	@Test
	@DisplayName("Deve buscar fatura com base no ID cartao de crédito")
	public void deveBuscarFaturaPeloCartao() {
		assertThat(faturaService.findByCartaoCreditoId(2L).size()).isEqualTo(1);
		
	}
	
	@Test
	@DisplayName("Deve buscar fatura com base no ID da fatura")
	public void deveBuscarFaturaPeloId() {
		assertThat(faturaService.find(2L).getCartaoCredito().getId()).isEqualTo(2);
		
	}
	
	@Test
	@DisplayName("Deve dar erro ao tentar excluir fatura")
	public void deveApresentarErroAoTentarExcluirFatura() {
		Assertions.assertThrows(DataIntegrityException.class, () -> {
			faturaService.delete(2L);
		});
	}
}
