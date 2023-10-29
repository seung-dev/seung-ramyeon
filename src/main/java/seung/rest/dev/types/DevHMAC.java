package seung.rest.dev.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class DevHMAC extends SType {

	@NotBlank
	@JsonProperty
	private String algorithm;
	
	@NotBlank
	@JsonProperty
	private String url_path;
	
	@NotBlank
	@JsonProperty
	private String access_key;
	
	@NotBlank
	@JsonProperty
	private String secret_key;
	
}
