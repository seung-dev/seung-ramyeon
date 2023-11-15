package seung.rest.hist.service;

import org.springframework.http.ResponseEntity;

import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.hist.types.HistT010000;

public interface HistS {

	/**
	 * <h1>Description</h1>
	 * <pre>
	 * REST 사용내역 조회
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link HistT010000}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> hist_t010000(final SRequestAttribute request_attribute, HistT010000 request_body) throws Exception;
	
}
