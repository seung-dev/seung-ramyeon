package seung.rest.admin;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.admin.service.AdminS;
import seung.rest.admin.types.AdminT010010;
import seung.rest.admin.types.AdminT010000;
import seung.rest.admin.types.AdminT010020;
import seung.rest.admin.types.AdminT010021;
import seung.rest.admin.types.AdminT010030;
import seung.rest.admin.types.AdminT011020;

@Controller
@Slf4j
public class AdminC {

	@Resource(name = "adminS")
	private AdminS adminS;
	
	@PostMapping(value = {"/rest/admin/t010000"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t010000(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT010000 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t010000(request_attribute, request_body);
	}//end of admin_t010000
	
	@PostMapping(value = {"/rest/admin/t010010"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t010010(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT010010 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t010010(request_attribute, request_body);
	}//end of admin_t010010
	
	@PostMapping(value = {"/rest/admin/t010020"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t010020(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT010020 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t010020(request_attribute, request_body);
	}//end of admin_t010020
	
	@PostMapping(value = {"/rest/admin/t010021"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t010021(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT010021 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t010021(request_attribute, request_body);
	}//end of admin_t010021
	
	@PostMapping(value = {"/rest/admin/t010030"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t010030(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT010030 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t010030(request_attribute, request_body);
	}//end of admin_t010030
	
	@GetMapping(value = {"/rest/admin/t011000"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t011000(
			@RequestAttribute final SRequestAttribute request_attribute
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t011000(request_attribute);
	}//end of admin_t011000
	
	@PostMapping(value = {"/rest/admin/t011020"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> admin_t011020(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final AdminT011020 request_body
			) throws Exception {
		log.debug("run");
		
		return adminS.admin_t011020(request_attribute, request_body);
	}//end of admin_t011020
	
}
