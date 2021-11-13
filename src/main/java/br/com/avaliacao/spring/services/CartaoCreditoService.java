package br.com.avaliacao.spring.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@Service
public class CartaoCreditoService {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private AutorizadoraService autorizadoraService;

	@Autowired
	private FaturaService faturaService;

	@Autowired
	private TransacaoService transacaoService;

	public CartaoCredito find(Long id) {
		Optional<CartaoCredito> obj = cartaoCreditoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CartaoCredito.class.getName()));
	}

	public List<CartaoCredito> findByNome(String nome) {
		return cartaoCreditoRepository.findByNome(nome);
	}

	public List<CartaoCredito> findByAlunoId(Long alunoId) {
		return cartaoCreditoRepository.findByAlunoId(alunoId);
	}

	public CartaoCredito findByNumero(String numero) {
		Optional<CartaoCredito> obj = cartaoCreditoRepository.findByNumero(numero);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Número: " + numero + ", Tipo: " + CartaoCredito.class.getName()));
	}

	public List<CartaoCredito> findAll() {
		return cartaoCreditoRepository.findAll();
	}

	public CartaoCredito insert(CartaoCredito cartaoCredito) {
		cartaoCredito.setId(null);
		return cartaoCreditoRepository.save(cartaoCredito);
	}

	public CartaoCredito update(CartaoCredito cartaoCredito) {
		CartaoCredito novoCartaoCredito = find(cartaoCredito.getId());
		updateData(novoCartaoCredito, cartaoCredito);
		return cartaoCreditoRepository.save(novoCartaoCredito);
	}

	public void delete(Long id) {
		find(id);
		try {
			cartaoCreditoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	public Transacao transacao(@Valid TransacaoCartaoCreditoDTO objDTO) {
		CartaoCredito cartaoCredito = autorizadoraService.autorizar(objDTO);
		Fatura fatura = faturaService.findFaturaAtualByCartaoCreditoId(cartaoCredito.getId());
		if (fatura == null) {
			fatura = faturaService.insert(new Fatura(cartaoCredito));
		}
		return transacaoService.insert(new Transacao(objDTO, fatura));
	}

	private void updateData(CartaoCredito novoCartaoCredito, CartaoCredito cartaoCredito) {
		novoCartaoCredito.setAluno(cartaoCredito.getAluno());
		novoCartaoCredito.setNumero(cartaoCredito.getNumero());
		novoCartaoCredito.setNome(cartaoCredito.getNome());
		novoCartaoCredito.setVencimento(cartaoCredito.getVencimento());
		novoCartaoCredito.setLimite(cartaoCredito.getLimite());
		novoCartaoCredito.setCodigoSeguranca(cartaoCredito.getCodigoSeguranca());
	}

}
