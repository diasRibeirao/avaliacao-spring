package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.dto.AlunoAtualizarDTO;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class AlunoAtualizarValidator implements ConstraintValidator<AlunoAtualizar, AlunoAtualizarDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public void initialize(AlunoAtualizar ann) {
	}

	@Override
	public boolean isValid(AlunoAtualizarDTO AlunoAtualizarDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Aluno aux = alunoRepository.findByMatricula(AlunoAtualizarDTO.getMatricula());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("matricula", "Matrícula já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
