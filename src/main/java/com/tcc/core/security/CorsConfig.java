package com.tcc.core.security;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		//config.setAllowedOrigins(Arrays.asList("https://dfe.chronoserp.com.br","https://homolog-dfe.chronoserp.com.br"));
		config.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS", "PATCH"));
		config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
		config.setExposedHeaders(List.of( "Content-Disposition"));
		config.setMaxAge(Duration.ofMinutes(3600));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;
	}

}
