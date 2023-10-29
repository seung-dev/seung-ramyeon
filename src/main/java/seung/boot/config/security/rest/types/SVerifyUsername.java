package seung.boot.config.security.rest.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class SVerifyUsername extends SType {

	@Email
	@NotBlank
	@JsonProperty
	private String username;
	
}
