package br.com.avaliacao.spring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/v1/faturas", produces = "application/json" )
@Tag(name = "Faturas")
public class FaturaResource {

	@Autowired
	private FaturaService faturaService;

	@Autowired
	private FaturaConverter converter;

	@Operation(summary = "Listar todos as faturas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FaturaDTO>> findAll() {
		List<FaturaDTO> list = converter.Parse(faturaService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar a fatura pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FaturaDTO> find(@PathVariable Long id) {
		FaturaDTO obj = converter.Parse(faturaService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Buscar faturas pelo id do cartão de drédito")
	@RequestMapping(value = "/cartoes/{cartaoCreditoId}", method = RequestMethod.GET)
	public ResponseEntity<List<FaturaDTO>> findByCartaoCreditoId(@PathVariable Long cartaoCreditoId) {
		List<FaturaDTO> list = converter.Parse(faturaService.findByCartaoCreditoId(cartaoCreditoId));
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Adicionar uma fatura")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FaturaAdicionarDTO faturaAdicionarDTO) {
		Fatura fatura = converter.ParseAdicionarDTO(faturaAdicionarDTO);
		fatura = faturaService.insert(fatura);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fatura.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Atualizar uma fatura")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody FaturaAtualizarDTO faturaAtualizarDTO) {
		Fatura obj = converter.ParseFaturaAtualizarDTO(faturaAtualizarDTO);
		obj.setId(id);
		obj = faturaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Pagar uma fatura")
	@RequestMapping(value = "/pagar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> pagar(@PathVariable Long id, @Valid @RequestBody FaturaPagarDTO faturaPagarDTO) {
		Fatura fatura = faturaService.find(id);
		faturaService.pagar(fatura, faturaPagarDTO.getValorPago());
		return ResponseEntity.ok().body("Fatura paga com sucesso.");
	}
	
	@Operation(summary = "Gerar extrato de uma fatura")
	@RequestMapping(value = "/extrato/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> extrato(@PathVariable Long id) {
		Fatura fatura = faturaService.find(id);

		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.cacheControl(CacheControl.noCache())
				.header("Content-Disposition", "attachment; filename=extrato.pdf")
				.body(new InputStreamResource(faturaService.gerarExtrato(fatura)));
	}

	@Operation(summary = "Deletar uma fatura")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		faturaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
