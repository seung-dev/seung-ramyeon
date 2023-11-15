package seung.rest.hist;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.rest.hist.service.HistS;
import seung.rest.hist.types.HistT010000;

@Controller
@Slf4j
public class HistC {

	@Resource(name = "histS")
	private HistS histS;
	
	@PostMapping(value = {"/rest/hist/t010000"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SResponseBody> hist_t010000(
			@RequestAttribute final SRequestAttribute request_attribute
			, @Valid @RequestBody final HistT010000 request_body
			) throws Exception {
		log.debug("run");
		
		return histS.hist_t010000(request_attribute, request_body);
	}//end of hist_t010000
	
}
