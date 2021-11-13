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

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.FaturaAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.FaturaAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.FaturaDTO;
import br.com.avaliacao.spring.domain.dto.FaturaPagarDTO;
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

	@ApiOperation(value = "Buscar faturas pelo id do cartão de drédito", tags = { "Faturas" })
	@RequestMapping(value = "/cartoes/{cartaoCreditoId}", method = RequestMethod.GET)
	public ResponseEntity<List<FaturaDTO>> findByCartaoCreditoId(@PathVariable Long cartaoCreditoId) {
		List<FaturaDTO> list = converter.Parse(faturaService.findByCartaoCreditoId(cartaoCreditoId));
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Adicionar uma fatura", tags = { "Faturas" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FaturaAdicionarDTO faturaAdicionarDTO) {
		Fatura fatura = converter.ParseAdicionarDTO(faturaAdicionarDTO);
		fatura = faturaService.insert(fatura);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fatura.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar uma fatura", tags = { "Faturas" })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody FaturaAtualizarDTO faturaAtualizarDTO) {
		Fatura obj = converter.ParseFaturaAtualizarDTO(faturaAtualizarDTO);
		obj.setId(id);
		obj = faturaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Pagar uma fatura", tags = { "Faturas" })
	@RequestMapping(value = "/pagar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> pagar(@PathVariable Long id, @Valid @RequestBody FaturaPagarDTO faturaPagarDTO) {
		Fatura fatura = faturaService.find(id);
		faturaService.pagar(fatura, faturaPagarDTO.getValorPago());
		return ResponseEntity.ok().body("Fatura paga com sucesso.");
	}

	@ApiOperation(value = "Deletar uma fatura", tags = { "Faturas" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		faturaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
