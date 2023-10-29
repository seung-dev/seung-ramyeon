package seung.boot.config.security.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.types.SResponseAdvice;

@Component(value = "sAuthenticationEntryPoint")
@Slf4j
public class SAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(
			HttpServletRequest request
			, HttpServletResponse response
			, AuthenticationException exception
			) throws IOException, ServletException {
		log.debug("run");
		
		HttpStatus http_status = HttpStatus.UNAUTHORIZED;
		
		response.setStatus(http_status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(
				SResponseAdvice.builder()
					.timestamp(System.currentTimeMillis())
					.status(http_status.value())
					.error(http_status.getReasonPhrase())
					.message("")
					.path(request.getRequestURI())
					.filter("SAuthenticationEntryPoint")
					.build()
					.stringify()
				);
		
		response.getWriter().flush();
	}// end of commence
	
}
