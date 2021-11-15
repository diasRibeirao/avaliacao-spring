package br.com.avaliacao.spring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.dto.AlunoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.AlunoAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.AlunoDTO;
import br.com.avaliacao.spring.domain.dto.converter.AlunoConverter;
import br.com.avaliacao.spring.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/v1/alunos", produces = "application/json" )
@Tag(name = "Alunos")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AlunoConverter converter;

	@Operation(summary = "Listar todos os alunos")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AlunoDTO>> findAll() {
		List<AlunoDTO> list = converter.Parse(alunoService.findAll());
		return ResponseEntity.ok().body(list);
	} 

	@Operation(summary = "Buscar o aluno pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AlunoDTO> find(@PathVariable Long id) {
		AlunoDTO obj = converter.Parse(alunoService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Adicionar um aluno")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AlunoAdicionarDTO alunoAdicionarDTO) {
		Aluno aluno = converter.ParseAdicionarDTO(alunoAdicionarDTO);
		aluno = alunoService.insert(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Atualizar um aluno")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AlunoAtualizarDTO objDto, @PathVariable Long id) {
		Aluno obj = converter.ParseAtualizarDTO(objDto);
		obj.setId(id);
		obj = alunoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Deletar um aluno")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
