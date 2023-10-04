package seung.boot.config.web.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public class SResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final SResponseBody response_body;
	
	private final Exception exception;
	
	public String error_code() {
		return this.response_body.error_code();
	}// end of error_code
	
	public SError s_error() {
		return SError.resolve(this.response_body.error_code());
	}// end of s_error
	
	public String error_message() {
		return this.response_body.error_message();
	}// end of error_message
	
}
