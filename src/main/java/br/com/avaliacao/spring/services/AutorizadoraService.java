package br.com.avaliacao.spring.services;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.services.exceptions.AutorizadoraException;

@Service
public class AutorizadoraService {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	public CartaoCredito autorizar(TransacaoCartaoCreditoDTO objDTO) {
		Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findByNumero(objDTO.getNumeroCartaoCredito());

		if (cartaoCredito.isEmpty()) {
			throw new AutorizadoraException("Transação não autorizada. Cartão Inválido.");
		}

		if (!cartaoCredito.get().getCodigoSeguranca().equals(objDTO.getCodigoSegurancaCartaoCredito())) {
			throw new AutorizadoraException("Transação não autorizada. Código de segurança inválido.");
		}

		if (!cartaoCredito.get().getNome().equalsIgnoreCase(objDTO.getNomeImpresssoCartaoCredito())) {
			throw new AutorizadoraException("Transação não autorizada. Falha na validação dos dados.");
		}

		validaDataValidade(cartaoCredito.get().getVencimento(), objDTO.getVencimentoCartaoCredito());

		if (cartaoCredito.get().isBloqueado()) {
			throw new AutorizadoraException("Transação não autorizada. Cartão de crédito bloqueado.");
		}

		validaLimiteDisponivel(cartaoCredito.get(), objDTO.getValorCompra());

		return cartaoCredito.get();
	}

	private void validaLimiteDisponivel(CartaoCredito cartaoCredito, BigDecimal valorCompra) {
		if (valorCompra.compareTo(cartaoCredito.getValorDisponivel()) == 1) {
			throw new AutorizadoraException("Transação não autorizada. Limite excedido/sem saldo.");
		}
	}

	private void validaDataValidade(YearMonth vencimentoCartaoCredito, YearMonth vencimentoDados) {
		int monthCartaoCredito = vencimentoCartaoCredito.getMonthValue();
		int yearCartaoCredito = vencimentoCartaoCredito.getYear();

		int monthDados = vencimentoDados.getMonthValue();
		int yearDados = vencimentoDados.getYear();

		if (monthCartaoCredito != monthDados || yearCartaoCredito != yearDados) {
			throw new AutorizadoraException("Transação não autorizada. Falha na validação dos dados.");
		}

		Calendar calendar = Calendar.getInstance();
		int monthAtual = calendar.get(Calendar.MONTH) + 1;
		int yearAtual = calendar.get(Calendar.YEAR);

		if (yearAtual > yearCartaoCredito || (yearAtual == yearCartaoCredito && monthAtual > monthCartaoCredito)) {
			throw new AutorizadoraException("Transação não autorizada. Cartão de crédito expirado.");
		}
	}

	public void podeCancelarFatura(Fatura fatura) {
		if (fatura.getDataPagamento() != null) {
			throw new AutorizadoraException("Transação não autorizada. Fatura com pagamento realizado. Entrar em contato com a administradora do cartão de crédito.");
		}
	}
}