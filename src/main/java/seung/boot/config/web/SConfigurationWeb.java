package seung.boot.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import seung.kimchi.SJson;

@Configuration
@Slf4j
public class SConfigurationWeb extends WebMvcConfigurationSupport {

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		log.debug("run");
//		argumentResolvers.add(new SHandlerMethodArgumentResolver());
	}// end of addArgumentResolvers
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("run");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(false);
	}// end of addResourceHandlers
	
	@Primary
	@Bean
	ObjectMapper objectMapper() {
		return SJson.object_mapper();
	}// end of objectMapper
	
}
