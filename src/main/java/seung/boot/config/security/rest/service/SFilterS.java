package seung.boot.config.security.rest.service;

import java.security.Key;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface SFilterS {

	/**
	 * <h1>Description</h1>
	 * <pre>
	 * Permit All 체크
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link HttpServletRequest}
	 * @return {@link Boolean}
	 */
	boolean filter_permitall(HttpServletRequest request) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * Json Web Token 검증
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link HttpServletRequest}
	 * @return {@link Authentication}
	 */
	Authentication filter_jwt(HttpServletRequest request, HttpServletResponse response, Key jwt_rsa_public_key, Key jwt_rsa_private_key) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * Access Key 검증
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link HttpServletRequest}
	 * @return {@link Authentication}
	 */
	Authentication filter_access_key(HttpServletRequest request) throws Exception;
	
}
