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
public class AdminT010021 extends SType {

	@NotBlank
	@JsonProperty
	private String username;
	
}
