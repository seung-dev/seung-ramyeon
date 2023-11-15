package seung.rest.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.datasource.SMapper0;
import seung.boot.config.security.crypto.SBCryptPasswordEncoder;
import seung.boot.config.web.types.SError;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.boot.config.web.types.SResponseEntity;
import seung.kimchi.types.SLinkedHashMap;
import seung.rest.admin.service.AdminS;
import seung.rest.admin.types.AdminT010010;
import seung.rest.admin.types.AdminT010000;
import seung.rest.admin.types.AdminT010020;
import seung.rest.admin.types.AdminT010021;
import seung.rest.admin.types.AdminT010030;
import seung.rest.admin.types.AdminT011020;

@Service(value = "adminS")
@Slf4j
public class AdminSI implements AdminS {

	@Resource(name = "sMapper0")
	private SMapper0 sMapper0;
	
	@Resource(name = "sBCryptPasswordEncoder")
	private SBCryptPasswordEncoder sBCryptPasswordEncoder;
	
	@Override
	public ResponseEntity<SResponseBody> admin_t010000(
			final SRequestAttribute request_attribute
			, final AdminT010000 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				query.merge(request_body);
				
				response_body.merge(sMapper0.select_row("admin_t010000_00", query));
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t010000
	
	@Override
	public ResponseEntity<SResponseBody> admin_t010010(
			final SRequestAttribute request_attribute
			, final AdminT010010 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int item_size = 0;
		List<SLinkedHashMap> items = new ArrayList<>();
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				query.merge(request_body);
				query.add("page_offset", (request_body.page_no() - 1) * request_body.page_size());
				
				item_size = sMapper0.select_integer("admin_t010010_00", query);
				if(item_size > 0) {
					items.addAll(sMapper0.select_rows("admin_t010010_10", query));
				}
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("item_size", item_size);
			response_body.add("items", items);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t010010
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ResponseEntity<SResponseBody> admin_t010020(
			final SRequestAttribute request_attribute
			, final AdminT010020 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int admin_t010020_20 = 0;
		int admin_t010020_21 = 0;
		int admin_t010020_22 = 0;
		int admin_t010020_23 = 0;
		int admin_t010020_24 = 0;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			long epoch = new Date().getTime();
			
			while(true) {
				
				query.add("principal", request_attribute.principal());
				query.add("principal", "seung");// test
				query.merge(request_body);
				
				String user_no = String.format("%s%d%s", "U", epoch / 1000, RandomStringUtils.random(5, true, true));
				query.add("user_no", user_no);
				
				query.add("user_state", "A");
				admin_t010020_20 = sMapper0.insert("admin_t010020_20", query);
				if(admin_t010020_20 == 0) {
					response_body.error(SError.SIGNUP_FAILED);
					break;
				}
				
				query.add("user_no_updt", request_attribute.principal());
				admin_t010020_21 = sMapper0.insert("admin_t010020_21", query);
				
				query.add("password", sBCryptPasswordEncoder.encode(UUID.randomUUID().toString()));
				admin_t010020_22 = sMapper0.insert("admin_t010020_22", query);
				if(admin_t010020_22 == 0) {
					response_body.error(SError.USERNAME_IS_NOT_AVAILABLE);
					break;
				}
				
				admin_t010020_23 = sMapper0.insert("admin_t010020_23", query);
				
				String edit_no = String.format("P%d%s", epoch / 1000, RandomStringUtils.random(5, true, true));
				String edit_expr = Long.toString(epoch + 1000 * 60 * 10);
				
				query.add("edit_no", edit_no);
				query.add("edit_expr", edit_expr);
				admin_t010020_24 = sMapper0.insert("admin_t010020_24", query);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("admin_t010020_20", admin_t010020_20);
			response_body.add("admin_t010020_21", admin_t010020_21);
			response_body.add("admin_t010020_22", admin_t010020_22);
			response_body.add("admin_t010020_23", admin_t010020_23);
			response_body.add("admin_t010020_24", admin_t010020_24);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t010020
	
	@Override
	public ResponseEntity<SResponseBody> admin_t010021(
			final SRequestAttribute request_attribute
			, final AdminT010021 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int admin_t010021_20 = 0;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				query.merge(request_body);
				
				String edit_no = String.format("P%d%s", new Date().getTime() / 1000, RandomStringUtils.random(5, true, true));
				query.add("edit_no", edit_no);
				
				admin_t010021_20 = sMapper0.insert("admin_t010021_20", query);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("admin_t010021_20", admin_t010021_20);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t010021
	
	@Override
	public ResponseEntity<SResponseBody> admin_t010030(
			final SRequestAttribute request_attribute
			, final AdminT010030 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int admin_t010030_30 = 0;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				query.merge(request_body);
				
				admin_t010030_30 = sMapper0.update("admin_t010030_30", query);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("admin_t010030_30", admin_t010030_30);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t010030
	
	@Override
	public ResponseEntity<SResponseBody> admin_t011000(
			final SRequestAttribute request_attribute
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int item_size = 0;
		List<SLinkedHashMap> items = new ArrayList<>();
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				item_size = sMapper0.select_integer("admin_t011000_00", query);
				if(item_size > 0) {
					items.addAll(sMapper0.select_rows("admin_t011000_10", query));
				}
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("item_size", item_size);
			response_body.add("items", items);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t011000
	
	@Override
	public ResponseEntity<SResponseBody> admin_t011020(
			final SRequestAttribute request_attribute
			, final AdminT011020 request_body
			) throws Exception {
		log.debug("run");
		
		final String trace_id = request_attribute.trace_id();
		
		SResponseBody response_body = SResponseBody
				.builder(request_attribute)
				.build()
				;
		
		int admin_t011020_20 = 0;
		
		SLinkedHashMap query = new SLinkedHashMap();
		try {
			
			while(true) {
				
				query.merge(request_body);
				
				String org_no = String.format("%s%d%s", "O", new Date().getTime() / 1000, RandomStringUtils.random(5, true, true));
				query.add("org_no", org_no);
				
				admin_t011020_20 = sMapper0.insert("admin_t011020_20", query);
				
				response_body.success();
				break;
			}// end of while
			
		} catch (Exception e) {
			log.error("({}) exception=", trace_id, e);
		} finally {
			response_body.add("admin_t011020_20", admin_t011020_20);
		}// end of try
		
		return SResponseEntity.build(request_attribute, response_body.done());
	}// end of admin_t011020
	
}
