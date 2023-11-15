package seung.rest.admin.service;

import org.springframework.http.ResponseEntity;

import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.admin.types.AdminT010010;
import seung.rest.admin.types.AdminT010000;
import seung.rest.admin.types.AdminT010020;
import seung.rest.admin.types.AdminT010021;
import seung.rest.admin.types.AdminT010030;
import seung.rest.admin.types.AdminT011020;

public interface AdminS {

	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 사용자정보조회
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT010010}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t010000(final SRequestAttribute request_attribute, AdminT010000 request_body) throws Exception;
	
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 사용자목록조회
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT010010}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t010010(final SRequestAttribute request_attribute, AdminT010010 request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 사용자기본정보등록
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT010020}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t010020(final SRequestAttribute request_attribute, AdminT010020 request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 비밀번호변경 이메일발송
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT010021}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t010021(final SRequestAttribute request_attribute, AdminT010021 request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 사용자기본정보수정
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT010030}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t010030(final SRequestAttribute request_attribute, AdminT010030 request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 기업목록조회
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t011000(final SRequestAttribute request_attribute) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * 기업기본정보등록
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link AdminT011020}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> admin_t011020(final SRequestAttribute request_attribute, AdminT011020 request_body) throws Exception;
	
}
