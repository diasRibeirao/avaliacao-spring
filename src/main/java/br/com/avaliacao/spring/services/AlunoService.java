package br.com.avaliacao.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.services.exceptions.DataIntegrityException;
import br.com.avaliacao.spring.services.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno find(Long id) {
		Optional<Aluno> obj = alunoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno insert(Aluno aluno) {
		aluno.setId(null);
		return alunoRepository.save(aluno);
	}

	public Aluno update(Aluno aluno) {
		Aluno novoAluno = find(aluno.getId());
		updateData(novoAluno, aluno);
		return alunoRepository.save(novoAluno);
	}

	public void delete(Long id) {
		find(id);
		try {
			alunoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	private void updateData(Aluno novoAluno, Aluno aluno) {
		novoAluno.setMatricula(aluno.getMatricula());
		novoAluno.setNome(aluno.getNome());
	}
}
