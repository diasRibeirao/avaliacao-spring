package br.com.avaliacao.spring.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;

@SpringBootTest
@AutoConfigureMockMvc
class AutorizadoraResourcesTests {

	@Autowired
    private MockMvc mvc;
	
	@Test
    void testSecurity() throws Exception {
		mvc.perform(get("/v1/cartoes/transacao")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void autorizar() throws Exception {
		TransacaoCartaoCreditoDTO autorizacao = new TransacaoCartaoCreditoDTO(
				"1234567891234567","Emerson Dias de Oliveira",YearMonth.of(2023, 02),
				"123","Roupas",new BigDecimal(1000)
				);
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.findAndRegisterModules();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(autorizacao );
		mvc.perform(MockMvcRequestBuilders.post("/v1/cartoes/transacao")
					.contentType(MediaType.APPLICATION_JSON)
				       .content(requestJson)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao", is("Roupas")));
	}
	
	
	
}
