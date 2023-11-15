package seung.rest.hist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.datasource.SMapper0;
import seung.boot.config.web.types.SRequestAttribute;
import seung.boot.config.web.types.SResponseBody;
import seung.boot.config.web.types.SResponseEntity;
import seung.kimchi.types.SLinkedHashMap;
import seung.rest.hist.service.HistS;
import seung.rest.hist.types.HistT010000;

@Service(value = "histS")
@Slf4j
public class HistSI implements HistS {

	@Resource(name = "sMapper0")
	private SMapper0 sMapper0;
	
	@Override
	public ResponseEntity<SResponseBody> hist_t010000(
			final SRequestAttribute request_attribute
			, final HistT010000 request_body
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
				
				item_size = sMapper0.select_integer("hist_t010000_00", query);
				if(item_size > 0) {
					items.addAll(sMapper0.select_rows("hist_t010000_10", query));
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
	}// end of hist_t010000
	
}
