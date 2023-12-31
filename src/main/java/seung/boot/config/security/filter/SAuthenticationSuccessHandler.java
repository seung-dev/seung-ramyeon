package seung.boot.config.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.security.types.SUser;
import seung.kimchi.types.SLinkedHashMap;

@Slf4j
public class SAuthenticationSuccessHandler extends ForwardAuthenticationSuccessHandler {

	public SAuthenticationSuccessHandler(
			String forward_url
			) {
		super(forward_url);
	}
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request
			, HttpServletResponse response
			, Authentication authentication
			) throws IOException, ServletException {
		log.debug("run");
		
		if(authentication != null) {
			SUser s_user = (SUser) authentication.getPrincipal();
			request.setAttribute("oauth2", new SLinkedHashMap()
					.add("provider", s_user.provider())
					.add("attributes", new SLinkedHashMap(s_user.getAttributes()))
					);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}// end of onAuthenticationSuccess
	
}
