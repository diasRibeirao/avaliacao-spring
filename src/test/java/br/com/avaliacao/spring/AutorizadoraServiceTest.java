package br.com.avaliacao.spring;

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
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO();
		autorizacao.setCodigoSegurancaCartaoCredito("634");
		autorizacao.setNomeImpresssoCartaoCredito("ANGELICA CRISTINA GARCIA");
		autorizacao.setNumeroCartaoCredito("1609651850505841");
		autorizacao.setValorCompra(new BigDecimal(100000000));
		autorizacao.setVencimentoCartaoCredito(YearMonth.of(2025, 07));

		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Cartão de crédito expirado.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de codigo de seguranca invaido ")
	public void deveApresentarErroCodigoSegurancaInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO();
		autorizacao.setCodigoSegurancaCartaoCredito("634");
		autorizacao.setNomeImpresssoCartaoCredito("ANGELICA CRISTINA GARCIA");
		autorizacao.setNumeroCartaoCredito("1609651850505841");
		autorizacao.setValorCompra(new BigDecimal(100000000));
		autorizacao.setVencimentoCartaoCredito(YearMonth.of(2025, 07));

		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Código de segurança inválido.");
	}
	
	@Test
	@DisplayName("Deve retornar apresentar erro de cartao invalido ")
	public void deveApresentarErroDeCartaoInvalido() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO();
		autorizacao.setCodigoSegurancaCartaoCredito("634");
		autorizacao.setNomeImpresssoCartaoCredito("ANGELICA CRISTINA GARCIA");
		autorizacao.setNumeroCartaoCredito("1609651850505841");
		autorizacao.setValorCompra(new BigDecimal(100000000));
		autorizacao.setVencimentoCartaoCredito(YearMonth.of(2025, 07));

		Assertions.assertThrows(AutorizadoraException.class, ()  -> {
			autorizadoraService.autorizar(autorizacao);
		}, "Transação não autorizada. Cartão de crédito expirado.");
	}
	
	@Test
	@DisplayName("Deve retornar autorizado ")
	public void deveRetornarOK() {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO();
		autorizacao.setCodigoSegurancaCartaoCredito("634");
		autorizacao.setNomeImpresssoCartaoCredito("ANGELICA CRISTINA GARCIA");
		autorizacao.setNumeroCartaoCredito("1609651850505841");
		autorizacao.setValorCompra(new BigDecimal(100000000));
		autorizacao.setVencimentoCartaoCredito(YearMonth.of(2025, 07));
//		verificar o retorno como vira e verificar ele 
		
	}
	


}
