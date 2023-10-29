package seung.boot.config.web.filter;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.rest.service.SRestS;
import seung.boot.config.web.types.SRequest;
import seung.boot.config.web.types.SRequestAttribute;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class SGateRequestFilter extends OncePerRequestFilter {

	@Resource(name = "sRestS")
	private SRestS sRestS;
	
	@Override
	protected void doFilterInternal(
			final HttpServletRequest request
			, final HttpServletResponse response
			, final FilterChain filterChain
			) throws ServletException, IOException {
		log.debug("run");
		
		SRequestAttribute request_attribute = SRequestAttribute.builder(request).build();
		final String trace_id = request_attribute.trace_id();
		
		log.info("({}) url={}", trace_id, request.getRequestURL());
		
		request.setAttribute(SRequest._S_REQUEST_ATTRIBUTE, request_attribute);
		
//		ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
		
		int status = -1;
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			status = response.getStatus();
		}// end of try
		
		try {
			sRestS.add_rest_hist(
					request_attribute
					, response.getStatus()//http_status
					);
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		}// end of try
		
//		wrapper.copyBodyToResponse();
		
		log.info("({}) status={}", trace_id, status);
	}// end of doFilterInternal
	
}
