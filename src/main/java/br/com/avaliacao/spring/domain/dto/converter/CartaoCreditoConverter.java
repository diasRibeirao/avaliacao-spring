package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoDTO;

@Service
public class CartaoCreditoConverter {

	public CartaoCredito ParseAtualizarDTO(CartaoCreditoAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new CartaoCredito(new Aluno(origin.getAlunoId()), origin.getNumero(), origin.getNome(),
				origin.getVencimento(), origin.getLimite(), origin.getCodigoSeguranca(), origin.getMelhorDiaCompra(),
				origin.isAtivo());
	}

	public List<CartaoCredito> ParseAtualizarDTO(List<CartaoCreditoAtualizarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAtualizarDTO(obj)).collect(Collectors.toList());
	}

	public CartaoCredito ParseAdicionarDTO(CartaoCreditoAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new CartaoCredito(new Aluno(origin.getAlunoId()), origin.getNumero(), origin.getNome(),
				origin.getVencimento(), origin.getLimite(), origin.getCodigoSeguranca(), origin.getMelhorDiaCompra(),
				origin.isAtivo());
	}

	public List<CartaoCredito> ParseAdicionarDTO(List<CartaoCreditoAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}

	public CartaoCreditoDTO Parse(CartaoCredito origin) {
		if (origin == null)
			return null;

		return new CartaoCreditoDTO(origin.getId(), origin.getAluno().getId(), origin.getNumero(), origin.getNome(),
				origin.getVencimento(), origin.getValorDisponivel(), origin.getLimite(), origin.getCodigoSeguranca(), origin.getMelhorDiaCompra(),
				origin.isBloqueado());
	}

	public List<CartaoCreditoDTO> Parse(List<CartaoCredito> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

	public CartaoCredito ParseDTO(CartaoCreditoDTO origin) {
		if (origin == null)
			return null;

		return new CartaoCredito(new Aluno(origin.getAlunoId()), origin.getNumero(), origin.getNome(),
				origin.getVencimento(), origin.getLimite(), origin.getCodigoSeguranca(), origin.getMelhorDiaCompra(),
				origin.isAtivo());
	}

	public List<CartaoCredito> ParseDTO(List<CartaoCreditoDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseDTO(obj)).collect(Collectors.toList());
	}

}
