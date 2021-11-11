package br.com.avaliacao.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.repositories.TransacaoRepository;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public Transacao find(Long id) {
		Optional<Transacao> obj = transacaoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Transacao.class.getName()));
	}

	public List<Transacao> findAll() {
		return transacaoRepository.findAll();
	}
	
	public List<Transacao> findByFaturaId(Long faturaId) {
		return transacaoRepository.findByFaturaId(faturaId);
	}

	public Transacao insert(Transacao transacao) {
		transacao.setId(null);
		return transacaoRepository.save(transacao);
	}

	public Transacao update(Transacao transacao) {
		Transacao novoTransacao = find(transacao.getId());
		updateData(novoTransacao, transacao);
		return transacaoRepository.save(novoTransacao);
	}

	public void delete(Long id) {
		find(id);
		try {
			transacaoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	private void updateData(Transacao novoTransacao, Transacao transacao) {		
		novoTransacao.setFatura(transacao.getFatura());
		novoTransacao.setDescricao(transacao.getDescricao());
		novoTransacao.setValor(transacao.getValor());
	}
}
