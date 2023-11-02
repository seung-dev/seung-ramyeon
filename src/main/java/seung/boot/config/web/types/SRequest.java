package seung.boot.config.web.types;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import jakarta.servlet.http.HttpServletRequest;
import seung.kimchi.SText;
import seung.kimchi.types.SHttpHeader;
import seung.kimchi.types.SLinkedHashMap;

public class SRequest {

	public static final String _S_REQUEST_ATTRIBUTE = "request_attribute";
	
	public static final String _S_TRACE_ID = "trace_id";
	
	public static String scheme(HttpServletRequest request) {
		String scheme = request.getHeader(SHttpHeader._S_X_FORWARDED_PROTO);
		if(!SText.is_empty(scheme)) {
			return scheme;
		}
		return request.getScheme();
	}// end of scheme
	
	public static String host(HttpServletRequest request) {
		String host = request.getHeader(SHttpHeader._S_X_FORWARDED_HOST);
		if(!SText.is_empty(host)) {
			return host;
		}
		host = request.getHeader(SHttpHeader._S_HOST);
		if(!SText.is_empty(host)) {
			return host;
		}
		return request.getServerName();
	}// end of host
	
	public static int port(HttpServletRequest request) {
		return request.getServerPort();
	}// end of port
	
	public static String remote_addr(HttpServletRequest request) {
		String remote_addr = request.getHeader(SHttpHeader._S_X_FORWARDED_FOR);
		if(!SText.is_empty(remote_addr)) {
			return remote_addr;
		}
		return request.getRemoteAddr();
	}// end of remote_addr
	
	public static SLinkedHashMap headers(HttpServletRequest request) {
		SLinkedHashMap headers = new SLinkedHashMap();
		Enumeration<String> header_names = request.getHeaderNames();
		String header_name = "";
		while(header_names.hasMoreElements()) {
			header_name = header_names.nextElement();
			if(header_name == null) {
				continue;
			}
			headers.add(header_name, request.getHeader(header_name));
		}// end of while
		return headers;
	}// end of headers
	
	public static String user_agent(HttpServletRequest request) {
		return request.getHeader(SHttpHeader._S_USER_AGENT);
	}// end of user_agent
	
	public static String principal(HttpServletRequest request) {
		Authentication authentication = (Authentication) request.getUserPrincipal();
		if(authentication == null) {
			return null;
		}
		return authentication.getName();
	}// end of principal
	
	public static List<String> roles(HttpServletRequest request) {
		List<String> roles = new ArrayList<String>();
		Authentication authentication = (Authentication) request.getUserPrincipal();
		if(authentication != null) {
			for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				roles.add(grantedAuthority.getAuthority());
			}
		}
		return roles;
	}// end of roles
	
	public static SRequestAttribute request_attribute(HttpServletRequest request) {
		return (SRequestAttribute) request.getAttribute(_S_REQUEST_ATTRIBUTE);
	}// end of request_attribute
	
}
