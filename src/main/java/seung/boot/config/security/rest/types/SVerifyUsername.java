package seung.boot.config.security.rest.types;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

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
