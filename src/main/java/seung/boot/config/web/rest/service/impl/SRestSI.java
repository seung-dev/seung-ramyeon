package seung.boot.config.web.rest.service.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.datasource.SMapper0;
import seung.boot.config.web.rest.service.SRestS;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SRequestBody;
import seung.boot.config.web.types.SResponseBody;
import seung.boot.config.web.types.SResponseEntity;
import seung.kimchi.types.SLinkedHashMap;

@Service(value = "sRestS")
@Slf4j
public class SRestSI implements SRestS {

	@Resource(name = "sMapper0")
	private SMapper0 sMapper0;
	
	@Override
	public ResponseEntity<SResponseBody> reflect_get(
			final SRequestAttribute request_attribute
			, final Map<String, Object> request_param
			, final SLinkedHashMap request_header
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		try {
			
			while(true) {
				
				response_body.add("request_header", request_header);
				response_body.add("request_attribute", request_attribute);
				response_body.add("request_param", request_param);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of reflect_get
	
	@Override
	public ResponseEntity<SResponseBody> reflect_post(
			final SRequestAttribute request_attribute
			, final SRequestBody request_body
			, final SLinkedHashMap request_header
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		try {
			
			while(true) {
				
				response_body.add("request_header", request_header);
				response_body.add("request_attribute", request_attribute);
				response_body.add("request_body", request_body);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of reflect_post
	
	@Override
	public int add_rest_hist(
			SRequestAttribute request_attribute
			, int http_status
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		int add_rest_hist = 0;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			query.merge(request_attribute);
			query.add("http_status", http_status);
			query.add("roles", String.join(",", request_attribute.roles()));
			
			add_rest_hist = sMapper0.insert("add_rest_hist", query);
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		}// end of try
		
		return add_rest_hist;
	}// end of add_rest_hist
	
}
