package seung.boot.config.security.filter;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;
import seung.boot.config.security.rest.service.SFilterS;
import seung.boot.config.web.types.SResponseAdvice;
import seung.kimchi.SCertificate;

@Slf4j
public class SSecurityFilter extends OncePerRequestFilter {

	private Key jwt_rsa_public_key;
	private Key jwt_rsa_private_key;
	private SFilterS sFilterS;
	
	public SSecurityFilter(
			Key jwt_rsa_public_key
			, Key jwt_rsa_private_key
			, SFilterS sFilterS
			) {
		this.jwt_rsa_public_key = jwt_rsa_public_key;
		this.jwt_rsa_private_key = jwt_rsa_private_key;
		this.sFilterS = sFilterS;
	}
	public SSecurityFilter(
			String jwt_rsa_public_key
			, String jwt_rsa_private_key
			, SFilterS sFilterS
			) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, DecoderException {
		this.jwt_rsa_public_key = SCertificate.public_key(jwt_rsa_public_key);
		this.jwt_rsa_private_key = SCertificate.private_key(jwt_rsa_private_key);
		this.sFilterS = sFilterS;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain
			) throws ServletException, IOException {
		log.debug("run");
		
		HttpStatus http_status = HttpStatus.UNAUTHORIZED;
		
		try {
			
			Authentication authentication = null;
			
			while(true) {
				
				if(sFilterS.filter_permitall(request)) {
					break;
				}
				
				authentication = sFilterS.filter_jwt(request, response, jwt_rsa_public_key, jwt_rsa_private_key);
				if(authentication != null) {
					break;
				}
				
				authentication = sFilterS.filter_access_key(request);
				if(authentication != null) {
					break;
				}
				
				break;
			}// end of while
			
			if(authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch (Exception e) {
			http_status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		switch (http_status) {
//			case UNAUTHORIZED:
//			case FORBIDDEN:
			case INTERNAL_SERVER_ERROR:
				response.setStatus(http_status.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().write(
						SResponseAdvice.builder()
							.timestamp(System.currentTimeMillis())
							.status(http_status.value())
							.error(http_status.getReasonPhrase())
							.message("")
							.path(request.getRequestURI())
							.filter("SAccessKeyRequestFilter")
							.build()
							.stringify()
						);
				response.getWriter().flush();
				break;
			default:
				filterChain.doFilter(request, response);
				break;
		}// end of switch
		
	}// end of doFilterInternal
	
}
