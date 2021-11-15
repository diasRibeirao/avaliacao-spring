package br.com.avaliacao.spring.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class FaturaResourcesTests {

	@Autowired
    private MockMvc mvc;
	
	@Test
    void testSecurity() throws Exception {
		mvc.perform(get("/v1/alunos")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
	
	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void listarFatura() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/faturas")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].dataFechamento", is("10/12/2021 03:00")));
	}
	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void listarUmaFatura() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/faturas/2")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dataFechamento", is("10/12/2021 03:00")));
	}
	
	
	
}
