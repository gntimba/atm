package com.mahlodi.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AtmApplication  implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
		registry.
				addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("**/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**");
//	}

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

}
