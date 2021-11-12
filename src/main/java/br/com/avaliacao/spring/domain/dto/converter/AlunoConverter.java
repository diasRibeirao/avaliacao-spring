package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.dto.AlunoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.AlunoAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.AlunoDTO;

@Service
public class AlunoConverter {

	public Aluno ParseAdicionarDTO(AlunoAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Aluno(origin.getMatricula(), origin.getNome());
	}

	public List<Aluno> ParseAdicionarDTO(List<AlunoAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}

	public Aluno ParseAtualizarDTO(AlunoAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new Aluno(origin.getMatricula(), origin.getNome());
	}

	public List<Aluno> ParseAtualizarDTO(List<AlunoAtualizarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAtualizarDTO(obj)).collect(Collectors.toList());
	}

	public AlunoDTO Parse(Aluno origin) {
		if (origin == null)
			return null;

		return new AlunoDTO(origin.getId(), origin.getMatricula(), origin.getNome());
	}

	public List<AlunoDTO> Parse(List<Aluno> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

}
