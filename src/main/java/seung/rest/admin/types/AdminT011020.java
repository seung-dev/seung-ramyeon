package seung.rest.admin.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class AdminT011020 extends SType {

	@NotBlank
	@JsonProperty
	private String org_domain;
	
	@JsonProperty
	private String org_no_prev;
	
	@JsonProperty
	private String org_name;
	
}
