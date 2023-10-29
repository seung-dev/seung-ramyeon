package seung.rest.dev;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.dev.service.DevS;
import seung.rest.dev.types.DevHMAC;
import seung.rest.dev.types.DevKeypair;

@Controller
@Slf4j
public class DevC {

	@Resource(name = "devS")
	private DevS devS;
	
	@PostMapping(value = {"/rest/dev/jwt/keypair"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> jwt_keypair(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final DevKeypair request_body
			) throws Exception {
		log.debug("run");
		
		return devS.jwt_keypair(request_attribute, request_body);
	}//end of keypair
	
	@RequestMapping(value = {"/rest/dev/hmac"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> hmac(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final DevHMAC request_body
			) throws Exception {
		log.debug("run");
		
		return devS.hmac(request_attribute, request_body);
	}//end of hmac
	
}
