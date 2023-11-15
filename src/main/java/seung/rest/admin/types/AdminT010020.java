package seung.rest.admin.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class AdminT010020 extends SType {

	@JsonProperty
	private String phone_number;
	
	@NotBlank
	@JsonProperty
	private String name_full;
	
	@Pattern(regexp = "(M|U)")
	@NotBlank
	@JsonProperty
	private String user_role;
	
	@NotBlank
	@JsonProperty
	private String username;
	
	@NotBlank
	@JsonProperty
	private String org_no;
	
}
