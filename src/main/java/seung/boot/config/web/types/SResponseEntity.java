package seung.boot.config.web.types;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SResponseEntity {

	public static ResponseEntity<SResponseBody> build(
			final HttpStatus http_status
			, final SRequestAttribute request_attribute
			, final HttpHeaders response_header
			, final SResponseBody response_body
			) {
		request_attribute.response_time(response_body.response_time());
		request_attribute.error_code(response_body.error_code());
		return ResponseEntity.status(http_status)
				.headers(response_header)
				.body(response_body)
				;
	}// end of build
	
	public static ResponseEntity<SResponseBody> build(
			final SRequestAttribute request_attribute
			, final HttpHeaders response_header
			, final SResponseBody response_body
			) {
		return build(
				HttpStatus.OK//http_status
				, request_attribute
				, response_header
				, response_body
				);
	}// end of build
	
	public static ResponseEntity<SResponseBody> build(
			final HttpStatus http_status
			, final SRequestAttribute request_attribute
			, final SResponseBody response_body
			) {
		request_attribute.response_time(response_body.response_time());
		request_attribute.error_code(response_body.error_code());
		return ResponseEntity.status(http_status)
				.body(response_body)
				;
	}// end of build
	
	public static ResponseEntity<SResponseBody> build(
			final SRequestAttribute request_attribute
			, final SResponseBody response_body
			) {
		return build(
				HttpStatus.OK//http_status
				, request_attribute
				, response_body
				);
	}// end of build
	
}
