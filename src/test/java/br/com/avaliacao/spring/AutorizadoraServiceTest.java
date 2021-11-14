package br.com.avaliacao.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;
import br.com.avaliacao.spring.services.AutorizadoraService;
import br.com.avaliacao.spring.services.CartaoCreditoService;
import br.com.avaliacao.spring.services.exceptions.AutorizadoraException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class AutorizadoraServiceTest {

	@Autowired
	private AutorizadoraService autorizadoraService;
	
	@Test
	@DisplayName("Deve apresentar erro de cartao expirado")
	public void deveApresentarErroCartaoExpirado() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1609651850505841","ANGELICA CRISTINA GARCIA",YearMonth.of(2020, 07),
				"634","Roupas",new BigDecimal(10000000)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Limite excedido/sem saldo.");
	}
	
	@Test
	@DisplayName("Deve apresentar erro de falha na validacao")
	public void deveApresentarErroFalhaValidacao() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1234567891234567","Emerson Dias de Oliveira",YearMonth.of(2023, 02),
				"123","Roupas",new BigDecimal(23232323)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Limite excedido/sem saldo.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de codigo de seguranca invaido ")
	public void deveApresentarErroCodigoSegurancaInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1234567891234567","Emerson Dias de Oliveira",YearMonth.of(2023, 02),
				"111","Roupas",new BigDecimal(1000)
				);

		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Código de segurança inválido.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de cartao invalido ")
	public void deveApresentarErroDeCartaoInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1234567891234567","Nome Errado",YearMonth.of(2023, 02),
				"123","Roupas",new BigDecimal(1000)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Cartão Inválido.");
	}
	
	@Test
	@DisplayName("Deve retornar autorizado ")
	public void deveRetornarOK() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1234567891234567","Emerson Dias de Oliveira",YearMonth.of(2023, 02),
				"123","Roupas",new BigDecimal(1000)
				);

		assertThat(autorizadoraService.autorizar(autorizacao).getNumero()).isEqualTo("1234567891234567");
		
	}
	


}
