package seung.rest.dev.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.dev.types.DevHMAC;
import seung.rest.dev.types.DevKeypair;

public interface DevS {

	/**
	 * <h1>Description</h1>
	 * <pre>
	 * RSA Key Pair
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
	ResponseEntity<SResponseBody> jwt_keypair(final SRequestAttribute request_attribute, DevKeypair request_body) throws Exception;
	
	/**
	 * <h1>Description</h1>
	 * <pre>
	 * HMAC
	 * </pre>
	 * <h1>Request</h1>
	 * <pre>
	 * </pre>
	 * <h1>Response</h1>
	 * <pre>
	 * </pre>
	 * @param {@link SRequestAttribute}
	 * @param {@link DevHMAC}
	 * @return {@link SResponseBody}
	 */
	ResponseEntity<SResponseBody> hmac(final SRequestAttribute request_attribute, final DevHMAC request_body) throws Exception;
	
}
