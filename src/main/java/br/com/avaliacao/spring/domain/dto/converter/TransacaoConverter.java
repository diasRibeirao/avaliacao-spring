package br.com.avaliacao.spring.domain.dto.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.dto.TransacaoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.TransacaoAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.TransacaoDTO;

@Service
public class TransacaoConverter {

	public Transacao ParseAtualizarDTO(TransacaoAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new Transacao(new Fatura(origin.getFaturaId()), origin.getDescricao(), origin.getData(),
				origin.getValor());
	}

	public List<Transacao> ParseAtualizarDTO(List<TransacaoAtualizarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAtualizarDTO(obj)).collect(Collectors.toList());
	}
	
	public Transacao ParseAdicionarDTO(TransacaoAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Transacao(new Fatura(origin.getFaturaId()), origin.getDescricao(), new Date(), origin.getValor());
	}

	public List<Transacao> ParseAdicionarDTO(List<TransacaoAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}
	
	public TransacaoDTO Parse(Transacao origin) {
		if (origin == null)
			return null;

		return new TransacaoDTO(origin.getId(), origin.getFatura().getId(), origin.getDescricao(), origin.getData(), origin.getValor());
	}

	public List<TransacaoDTO> Parse(List<Transacao> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}
	
	public Transacao ParseDTO(TransacaoDTO origin) {
		if (origin == null)
			return null;

		return new Transacao(origin.getId(), new Fatura(origin.getFaturaId()), origin.getDescricao(), origin.getData(),
				origin.getValor());
	}

	public List<Transacao> ParseDTO(List<TransacaoDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseDTO(obj)).collect(Collectors.toList());
	}

}
