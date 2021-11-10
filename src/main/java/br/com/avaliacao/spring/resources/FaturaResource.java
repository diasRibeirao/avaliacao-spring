package br.com.avaliacao.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.spring.domain.dto.FaturaDTO;
import br.com.avaliacao.spring.domain.dto.converter.FaturaConverter;
import br.com.avaliacao.spring.services.FaturaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Faturas", description = "APIs Faturas", tags = { "Faturas" })
@RequestMapping(value = "/v1/faturas")
public class FaturaResource {

	@Autowired
	private FaturaService faturaService;

	@Autowired
	private FaturaConverter converter;

	@ApiOperation(value = "Listar todos as faturas", tags = { "Faturas" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FaturaDTO>> findAll() {
		List<FaturaDTO> list = converter.Parse(faturaService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar a fatura pelo id", tags = { "Faturas" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FaturaDTO> find(@PathVariable Long id) {
		FaturaDTO obj = converter.Parse(faturaService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	/*
	@ApiOperation(value = "Adicionar uma transação", tags = { "Faturas" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FaturaAdicionarDTO faturaAdicionarDTO) {
		Fatura fatura = converter.ParseAdicionarDTO(faturaAdicionarDTO);
		fatura = faturaService.insert(fatura);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fatura.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar um aluno", tags = { "Faturas"})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody FaturaDTO objDto, @PathVariable Long id) {
		Fatura obj = converter.ParseDTO(objDto);
		obj.setId(id); 
		obj = faturaService.update(obj);
		return ResponseEntity.noContent().build();
	}	
*/
	@ApiOperation(value = "Deletar uma fatura", tags = { "Faturas"})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		faturaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
