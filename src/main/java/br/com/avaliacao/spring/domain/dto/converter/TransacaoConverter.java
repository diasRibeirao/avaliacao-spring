package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.dto.TransacaoDTO;
import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;

@Service
public class TransacaoConverter {

	public TransacaoDTO Parse(Transacao origin) {
		if (origin == null)
			return null;

		return new TransacaoDTO(origin.getId(), origin.getFatura().getId(), origin.getDescricao(), origin.getData(),
				origin.getValor(), origin.getSituacao());
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
				origin.getValor(), SituacaoTransacao.toEnum(origin.getSituacaoId()));
	}

	public List<Transacao> ParseDTO(List<TransacaoDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseDTO(obj)).collect(Collectors.toList());
	}

}
