package seung.boot.config.web.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class SRequestBody extends SType {

	@JsonProperty
	private Object data;
	
}
