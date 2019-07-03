package projeto.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import projeto.backend.rest.model.TokenFilter;


@SpringBootApplication
public class BackendApplication {


	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/v1/disciplina/getPerfil");
		filterRb.addUrlPatterns("/v1/disciplina/rankingLike");
		filterRb.addUrlPatterns("/v1/disciplina/rankingComentario");
		filterRb.addUrlPatterns("/v1/disciplina/addComentario");
		filterRb.addUrlPatterns("/v1/disciplina/like");


		return filterRb;
	}

	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);

	}
}
