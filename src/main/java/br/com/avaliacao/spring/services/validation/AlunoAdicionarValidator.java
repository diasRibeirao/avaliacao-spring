package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.dto.AlunoAdicionarDTO;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class AlunoAdicionarValidator implements ConstraintValidator<AlunoAdicionar, AlunoAdicionarDTO> {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public void initialize(AlunoAdicionar ann) {
	}

	@Override
	public boolean isValid(AlunoAdicionarDTO alunoAdicionarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Aluno aux = alunoRepository.findByMatricula(alunoAdicionarDTO.getMatricula());
		if (aux != null) {
			list.add(new FieldMessage("matricula", "Matrícula já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
