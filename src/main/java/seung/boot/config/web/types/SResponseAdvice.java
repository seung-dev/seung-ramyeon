package seung.boot.config.web.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Builder
@Accessors(fluent = true)
@Getter
public class SResponseAdvice extends SType {

	@JsonProperty
	private final long timestamp;
	
	@JsonProperty
	private final int status;
	
	@JsonProperty
	private final String error;
	
	@JsonProperty
	private final String message;
	
	@JsonProperty
	private final String path;
	
	@JsonProperty
	private final String filter;
	
}
