package seung.boot.config.security.types;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Builder
@Accessors(fluent = true)
@Getter
public class SOAuth2Client extends SType {

	@NotBlank
	@JsonProperty
	private final String client_id;
	
	@NotBlank
	@JsonProperty
	private final String authorization_grant_type;
	
	@NotBlank
	@JsonProperty
	private final String redirect_url;
	
	@JsonProperty
	private final String scope;
	
	@NotBlank
	@JsonProperty
	private final String authorization_url;
	
}
