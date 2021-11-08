package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.dto.AlunoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.AlunoDTO;

@Service
public class AlunoConverter {

	public Aluno ParseAdicionar(AlunoAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Aluno(origin.getMatricula(), origin.getNome());
	}

	public List<Aluno> ParseAdicionar(List<AlunoAdicionarDTO> origin) {
		if (origin == null)
			return null;
		
		return origin.stream().map(obj -> ParseAdicionar(obj)).collect(Collectors.toList());
	}
	
	public Aluno Parse(AlunoDTO origin) {
		if (origin == null)
			return null;

		return new Aluno(origin.getId(), origin.getMatricula(), origin.getNome());
	}

	public List<Aluno> Parse2(List<AlunoDTO> origin) {
		if (origin == null)
			return null;
		
		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

}
