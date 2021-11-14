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
import br.com.avaliacao.spring.services.exceptions.AutorizadoraException;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class AutorizadoraServiceTest {

	@Autowired
	private AutorizadoraService autorizadoraService;

	
	@Test
	@DisplayName("Deve apresentar erro de cartao expirado")
	public void deveApresentarErroCartaoExpirado() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1609651850505841","ANGELICA CRISTINA GARCIA",YearMonth.of(2025, 07),
				"634","Roupas",new BigDecimal(10000000)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Limite excedido/sem saldo.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de codigo de seguranca invaido ")
	public void deveApresentarErroCodigoSegurancaInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1609651850505841","ANGELICA CRISTINA GARCIA",YearMonth.of(2025, 07),
				"000","Roupas",new BigDecimal(10000000)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Código de segurança inválido.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de cartao invalido ")
	public void deveApresentarErroDeCartaoInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1111111111111","ANGELICA CRISTINA GARCIA",YearMonth.of(2025, 07),
				"634","Roupas",new BigDecimal(10000000)
				);


		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Cartão Inválido.");
	}
	
	@Test
	@DisplayName("Deve retornar autorizado ")
	public void deveRetornarOK() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1111111111111","ANGELICA CRISTINA GARCIA",YearMonth.of(2025, 07),
				"634","Roupas",new BigDecimal(10000000)
				);
		assertThat(autorizadoraService.autorizar(autorizacao));
//		verificar o retorno como vira e verificar ele 
		
	}
	


}
