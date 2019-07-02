package projeto.backend.rest.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket detalheApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("projeto.backend.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build());

        return docket;
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("API-Ucdb");
        apiInfoBuilder.description("API para os Usuários discutir sobre as disciplinas do curso de Ciência da Computação da UFCG.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para diversão!.");
        apiInfoBuilder.license("Licença - Open Source");
        apiInfoBuilder.licenseUrl("");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }
    private Contact contato() {

        return new Contact(
                "Rafael Dantas",
                "http://ucdb.zapto.org/index.html",
                "rafael.azevedo@ccc.ufcg.edu.br");
    }
}
