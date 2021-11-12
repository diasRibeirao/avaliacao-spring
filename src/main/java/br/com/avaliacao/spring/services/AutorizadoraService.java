package br.com.avaliacao.spring.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.AutorizadoraDTO;
import br.com.avaliacao.spring.domain.dto.AutorizadoraRetornoDTO;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.services.exceptions.AutorizadoraException;

@Service
public class AutorizadoraService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	public AutorizadoraRetornoDTO autorizar(AutorizadoraDTO dados) {
		Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findByNumero(dados.getNumeroCartaoCredito());
		
		if (cartaoCredito.isEmpty()) {
			throw new AutorizadoraException("Transação não autorizada. Cartão Inválido.");
		}
		
		if (!cartaoCredito.get().getCodigoSeguranca().equals(dados.getCodigoSegurancaCartaoCredito())) {
			throw new AutorizadoraException("Transação não autorizada. Código de segurança inválido.");
		}
		
		if (!validaDataValidade(cartaoCredito.get().getVencimento().getMonthValue(), cartaoCredito.get().getVencimento().getYear())) {
			throw new AutorizadoraException("Transação não autorizada. Cartão de crédito expirado.");
		}
			
			
			/*
			
			
			throw new AutorizadoraException("Transação não autorizada. Cartão de crédito bloqueado.");
			throw new AutorizadoraException("Transação não autorizada. Falha na validação dos dados.");
			throw new AutorizadoraException("Transação não autorizada. Limite excedido/sem saldo.");
			*/

		
		return new AutorizadoraRetornoDTO(1L, 1L);
	}

	private boolean validaDataValidade(int month, int year) {
		Calendar calendar = Calendar.getInstance();		/// fazer logica de vencimento
		return calendar.get(Calendar.YEAR) > year && (calendar.get(Calendar.MONTH) + 1) > month;
	}

}
