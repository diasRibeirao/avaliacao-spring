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
import br.com.avaliacao.spring.domain.dto.AlunoDTO;
import br.com.avaliacao.spring.domain.dto.converter.AlunoConverter;
import br.com.avaliacao.spring.services.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Alunos", description = "APIs Alunos", tags = { "Alunos" })
@RequestMapping(value = "/v1/alunos")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AlunoConverter converter;

	@ApiOperation(value = "Listar todos os alunos", tags = { "Alunos" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> findAll() {
		List<Aluno> list = alunoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar o aluno pelo id", tags = { "Alunos" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> find(@PathVariable Long id) {
		Aluno obj = alunoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Adicionar um aluno", tags = { "Alunos" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AlunoAdicionarDTO alunoAdicionarDTO) {
		Aluno aluno = converter.ParseAdicionar(alunoAdicionarDTO);
		aluno = alunoService.insert(aluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar um aluno", tags = { "Alunos" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AlunoDTO objDto, @PathVariable Long id) {
		Aluno obj = converter.Parse(objDto);
		obj.setId(id);
		obj = alunoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Deletar um aluno", tags = { "Alunos" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
