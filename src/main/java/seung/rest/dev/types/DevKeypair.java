package seung.rest.dev.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class DevKeypair extends SType {

	@NotBlank
	@JsonProperty
	private String algorithm;
	
	@NotNull
	@JsonProperty
	private Integer key_size;
	
}
