package seung.boot.config.security.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.security.rest.service.SSignS;
import seung.boot.config.security.rest.service.impl.SOAuth2UserService;
import seung.boot.config.security.rest.types.SSigninUsername;
import seung.boot.config.security.rest.types.SSignupUsername;
import seung.boot.config.security.rest.types.SVerifyUsername;
import seung.boot.config.web.types.SRequestAttribute;

@RestController
@Slf4j
public class SSignC {

	@Resource(name = "sSignS")
	private SSignS sSignS;
	
	@Resource(name = "sOAuth2UserService")
	private SOAuth2UserService sOAuth2UserService;
	
	@PostMapping(value = {"/rest/sign/up/username/verify"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verify_username(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final SVerifyUsername request_body
			) throws Exception {
		log.debug("run");
		return sSignS.verify_username(request_attribute, request_body);
	}// end of verify_username
	
	@PostMapping(value = {"/rest/sign/up/username"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signup_username(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final SSignupUsername request_body
			) throws Exception {
		log.debug("run");
		return sSignS.signup_username(request_attribute, request_body);
	}// end of signup_username
	
	@PostMapping(value = {"/rest/sign/in/username"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signin_username(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final SSigninUsername request_body
			) throws Exception {
		log.debug("run");
		return sSignS.signin_username(request_attribute, request_body);
	}// end of signin_username
	
	@GetMapping(value = {"/rest/sign/in/success"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signin_success(
			@RequestAttribute final SRequestAttribute request_attribute
			, final HttpServletRequest request
			) throws Exception {
		log.debug("run");
		return sSignS.signin_success(request_attribute, request);
	}// end of signin_success
	
	@GetMapping(value = {"/rest/sign/out"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signout(
			@RequestAttribute final SRequestAttribute request_attribute
			) throws Exception {
		log.debug("run");
		return sSignS.signout(request_attribute);
	}// end of signout
	
}
