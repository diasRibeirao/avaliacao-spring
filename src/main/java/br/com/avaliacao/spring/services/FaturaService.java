package br.com.avaliacao.spring.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.repositories.FaturaRepository;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;
import br.com.avaliacao.spring.utils.GerarExtratoFatura;

@Service
public class FaturaService {

	@Autowired
	private FaturaRepository faturaRepository;

	@Autowired
	private AutorizadoraService autorizadoraService;

	public Fatura find(Long id) {
		Optional<Fatura> obj = faturaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Fatura.class.getName()));
	}

	public List<Fatura> findAll() {
		return faturaRepository.findAll();
	}

	public List<Fatura> findByCartaoCreditoId(Long cartaoCreditoId) {
		return faturaRepository.findByCartaoCreditoId(cartaoCreditoId);
	}

	public Fatura findFaturaAtualByCartaoCreditoId(Long cartaoCreditoId) {
		return faturaRepository.findFaturaAtualByCartaoCreditoId(cartaoCreditoId);
	}

	public Fatura insert(Fatura fatura) {
		fatura.setId(null);
		return faturaRepository.save(fatura);
	}

	public Fatura update(Fatura fatura) {
		Fatura novoFatura = find(fatura.getId());
		updateData(novoFatura, fatura);
		return faturaRepository.save(novoFatura);
	}

	public void pagar(Fatura fatura, BigDecimal valorPago) {
		autorizadoraService.podePagarFatura(fatura, valorPago);
		fatura.setDataPagamento(Calendar.getInstance().getTime());
		fatura.setValorPago(valorPago);
		faturaRepository.save(fatura);
	}

	public void delete(Long id) {
		find(id);
		try {
			faturaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	private void updateData(Fatura novoFatura, Fatura fatura) {
		novoFatura.setCartaoCredito(fatura.getCartaoCredito());
		novoFatura.setDataFechamento(fatura.getDataFechamento());
		novoFatura.setDataVencimento(fatura.getDataVencimento());
		novoFatura.setDataPagamento(fatura.getDataPagamento());
		novoFatura.setValorPago(fatura.getValorPago());
	}

	public InputStream gerarExtrato(Fatura fatura) {
		return GerarExtratoFatura.gerar(fatura);
	}

}
