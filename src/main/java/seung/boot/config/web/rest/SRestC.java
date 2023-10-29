package seung.boot.config.web.rest;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.rest.service.SRestS;
import seung.boot.config.web.types.SRequest;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SRequestBody;
import seung.boot.config.web.types.SResponseBody;

@Controller
@Slf4j
public class SRestC {

	@Resource(name = "sRestS")
	private SRestS sRestS;
	
	@GetMapping(value = {"/rest/reflect/get"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> reflect_get(
			@RequestAttribute final SRequestAttribute request_attribute
			, @RequestParam(required = false) final Map<String, Object> request_param
			, HttpServletRequest request
			) throws Exception {
		log.debug("run");
		
		return sRestS.reflect_get(request_attribute, request_param, SRequest.headers(request));
	}//end of reflect_get
	
	@PostMapping(value = {"/rest/reflect/post"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> reflect_post(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final SRequestBody request_body
			, HttpServletRequest request
			) throws Exception {
		log.debug("run");
		
		return sRestS.reflect_post(request_attribute, request_body, SRequest.headers(request));
	}//end of reflect_post
	
}
