package seung.rest.dev.types;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
