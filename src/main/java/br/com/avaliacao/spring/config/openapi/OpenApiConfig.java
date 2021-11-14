package br.com.avaliacao.spring.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	private static final String SCHEME_NAME = "basicAuth";
	private static final String SCHEME = "basic";

	@Bean
	public OpenAPI customOpenAPI() {
		var openApi = new OpenAPI().info(getInfo());

		addSecurity(openApi);

		return openApi;
	}

	private Info getInfo() {
		return new Info()
				.title("Avaliação - Spring Framework")
				.description("Projeto para avaliação final da disciplina de Spring Framework - Prof. Fabio Tadashi Miyasato")
				.version("1.0.0").license(getLicense());
	}
	
	private License getLicense() {
		return new License().name("Unlicense").url("https://unlicense.org/");
	}

	private void addSecurity(OpenAPI openApi) {
		var components = createComponents();
		var securityItem = new SecurityRequirement().addList(SCHEME_NAME);

		openApi.components(components).addSecurityItem(securityItem);
	}

	private Components createComponents() {
		var components = new Components();
		components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());

		return components;
	}

	private SecurityScheme createSecurityScheme() {
		return new SecurityScheme().name(SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme(SCHEME);
	}
}
