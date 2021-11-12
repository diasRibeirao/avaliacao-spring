package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.FaturaAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.FaturaDTO;
import br.com.avaliacao.spring.domain.dto.FaturaAtualizarDTO;

@Service
public class FaturaConverter {

	public Fatura ParseFaturaAtualizarDTO(FaturaAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new Fatura(new CartaoCredito(origin.getCartaoCreditoId()), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorPago());
	}

	public List<Fatura> ParseFaturaAtualizarDTO(List<FaturaAtualizarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseFaturaAtualizarDTO(obj)).collect(Collectors.toList());
	}
	
	public Fatura ParseAdicionarDTO(FaturaAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Fatura(new CartaoCredito(origin.getCartaoCreditoId()), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorPago());
	}

	public List<Fatura> ParseAdicionarDTO(List<FaturaAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}

	public FaturaAtualizarDTO ParseFaturaAtualizar(Fatura origin) {
		if (origin == null)
			return null;

		return new FaturaAtualizarDTO(origin.getCartaoCredito().getId(), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorPago());
	}

	public List<FaturaAtualizarDTO> ParseFaturaAtualizar(List<Fatura> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseFaturaAtualizar(obj)).collect(Collectors.toList());
	}
	
	public FaturaDTO Parse(Fatura origin) {
		if (origin == null)
			return null;

		return new FaturaDTO(origin.getId(), origin.getCartaoCredito().getId(), origin.getDataFechamento(), origin.getDataVencimento(), 
				origin.getDataPagamento(), origin.getValorTotal(), origin.getValorPago(), origin.isVencida());
	}

	public List<FaturaDTO> Parse(List<Fatura> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

}
