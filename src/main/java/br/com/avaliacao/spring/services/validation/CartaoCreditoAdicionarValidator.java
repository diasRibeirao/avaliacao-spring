package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAdicionarDTO;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class CartaoCreditoAdicionarValidator implements ConstraintValidator<CartaoCreditoAdicionar, CartaoCreditoAdicionarDTO> {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Override
	public void initialize(CartaoCreditoAdicionar ann) {
	}

	@Override
	public boolean isValid(CartaoCreditoAdicionarDTO cartaoCreditoAdicionarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		if (cartaoCreditoAdicionarDTO.getAlunoId() != null) {
			Optional<Aluno> aluno = alunoRepository.findById(cartaoCreditoAdicionarDTO.getAlunoId());
			if (aluno.isEmpty()) {
				list.add(new FieldMessage("alunoId", "Objeto não encontrado! Id: " + cartaoCreditoAdicionarDTO.getAlunoId() + ", Tipo: " + Aluno.class.getName()));
			}
		}
		
		if (!StringUtils.isBlank(cartaoCreditoAdicionarDTO.getNumero())) {
			Optional<CartaoCredito> aux = cartaoCreditoRepository.findByNumero(cartaoCreditoAdicionarDTO.getNumero());
			if (aux.isPresent()) {
				list.add(new FieldMessage("numero", "Número já existente!"));
			}		 
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
