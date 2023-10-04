package seung.boot.config.web.types;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import seung.kimchi.SText;
import seung.kimchi.types.SLinkedHashMap;

public class SRequest {

	public static final String _S_X_FORWARDED_PROTO = "x-forwarded-proto";
	
	public static final String _S_HOST = "host";
	
	public static final String _S_X_FORWARDED_PORT = "x-forwarded-port";
	
	public static final String[] _S_X_FORWARDED_FOR = {
			"x-forwarded-for"
			, "proxy-client-ip"
			, "http_x_forwarded_for"
			, "http_client_ip"
			, "wl_proxy_client_ip"
	};
	
	public static final String _S_USER_AGENT = "user-agent";
	
	public static final String _S_REQUEST_ATTRIBUTE = "request_attribute";
	
	public static final String _S_TRACE_ID = "trace_id";
	
	public static String scheme(HttpServletRequest request) {
		String scheme = request.getHeader(_S_X_FORWARDED_PROTO);
		if(!SText.is_empty(scheme)) {
			return scheme;
		}
		return request.getScheme();
	}// end of scheme
	
	public static String host(HttpServletRequest request) {
		String host = request.getHeader(_S_HOST);
		if(!SText.is_empty(host)) {
			return host;
		}
		return request.getServerName();
	}// end of host
	
	public static int port(HttpServletRequest request) {
		String port = request.getHeader(_S_X_FORWARDED_PORT);
		if(!SText.is_empty(port)) {
			return Integer.parseInt(port);
		}
		return request.getServerPort();
	}// end of port
	
	public static String x_forwarded_for(HttpServletRequest request) {
		String remote_addr = "";
		for(String header_name : _S_X_FORWARDED_FOR) {
			remote_addr = request.getHeader(header_name);
			if(SText.is_empty(remote_addr)) {
				continue;
			}
			if("unknown".equalsIgnoreCase(remote_addr)) {
				continue;
			}
			return remote_addr;
		}// end of header_names
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
		return request.getHeader(_S_USER_AGENT);
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
