package seung.rest.dev.service.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.boot.config.web.types.SResponseEntity;
import seung.kimchi.SConvert;
import seung.kimchi.SSecurity;
import seung.rest.dev.service.DevS;
import seung.rest.dev.types.DevHMAC;
import seung.rest.dev.types.DevKeypair;

@Service(value = "devS")
@Slf4j
public class DevSI implements DevS {

	@Override
	public ResponseEntity<SResponseBody> jwt_keypair(
			final SRequestAttribute request_attribute
			, final DevKeypair request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		String public_key = "";
		String private_key = "";
		
		try {
			
			KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
			
			public_key = SConvert.encode_hex(keyPair.getPublic().getEncoded());
			private_key = SConvert.encode_hex(keyPair.getPrivate().getEncoded());
			
			response_body.success();
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("public_key", public_key);
			response_body.add("private_key", private_key);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body);
	}// end of keypair
	
	@Override
	public ResponseEntity<SResponseBody> hmac(
			final SRequestAttribute request_attribute
			, final DevHMAC request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		long timestamp = 0;
		String access_key = "";
		String signature = "";
		
		try {
			
			access_key = request_body.access_key();
			timestamp = System.currentTimeMillis();
			
			while(true) {
				
				String message = String.join(" ", request_body.url_path(), Long.toString(timestamp), request_body.access_key());
				Charset charset = StandardCharsets.UTF_8;
				
				signature = SConvert.encode_hex(
						SSecurity.hmac(
								request_body.algorithm()//algorithm
								, BouncyCastleProvider.PROVIDER_NAME//provider
								, request_body.secret_key()//key
								, message
								, charset
								)
						);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("timestamp", timestamp);
			response_body.add("access_key", access_key);
			response_body.add("signature", signature);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body);
	}// end of hmac

}
