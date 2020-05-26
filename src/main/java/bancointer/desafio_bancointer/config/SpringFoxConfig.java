package bancointer.desafio_bancointer.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "REST API do Desafio do Banco Inter", 
	      "CRUD de Usuários com Dígitos Únicos, inclui CACHE 'from scratch' e criptografia.", 
	      "", 
	      "", 
	      new Contact("João Paulo Leonel Alves", "", "jaoleonel@gmail.com"), 
	      "License of API", "Free License", Collections.emptyList());
	}
}

