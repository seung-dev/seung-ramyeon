package seung.boot;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.boot.types.SBuildProperties;
import seung.boot.types.SEnvironment;
import seung.kimchi.SSecurity;
import seung.kimchi.types.SLinkedHashMap;

@Component
@Slf4j
public class BootP {

	@Resource(name = "sBuildProperties")
	private SBuildProperties sBuildProperties;
	
	@Resource(name="sEnvironment")
	private SEnvironment sEnvironment;
	
	@Resource(name="sCache")
	private SLinkedHashMap sCache;
	
	@PostConstruct
	public void post_construct() {
		log.debug("run");
		
		try {
			
			Unirest.config().verifySsl(false);
			SSecurity.add_bouncy_castle_provider();
			
			log.warn("sBuildProperties={}", sBuildProperties.stringify());
			
			if(sEnvironment.is_loc()) {
				log.info("sEnvironment={}", sEnvironment.stringify(true));
			}
			
		} catch (Exception e) {
			log.error("exception=", e);
		}// end of try
		
	}// end of post_construct
	
	@PreDestroy
	public void pre_destroy() {
		log.debug("run");
		
		try {
			
			log.warn("shutdown.environment={}", sEnvironment.stringify());
			log.warn("shutdown.message={}", sEnvironment.shutdown_message());
			
		} catch (Exception e) {
			log.error("exception=", e);
		}// end of try
		
	}// end of pre_destroy
	
}
