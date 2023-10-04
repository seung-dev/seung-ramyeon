package seung.boot.config.web.rest.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SRequestBody;
import seung.boot.config.web.types.SResponseBody;
import seung.kimchi.types.SLinkedHashMap;

public interface SRestS {

	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 요청 반사
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link Map}
	 * @param {@link SLinkedHashMap}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> reflect_get(final SRequestAttribute request_attribute, final Map<String, Object> request_param, final SLinkedHashMap request_header) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 요청 반사
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link SRequestBody}
	 * @param {@link SLinkedHashMap}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> reflect_post(final SRequestAttribute request_attribute, final SRequestBody request_body, final SLinkedHashMap request_header) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 요청내역 등록
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @return {@link Authentication}
	 */
	int add_rest_hist(SRequestAttribute request_attribute, int http_status) throws Exception;
	
}
