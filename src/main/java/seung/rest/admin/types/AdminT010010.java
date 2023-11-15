package seung.rest.admin.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class AdminT010010 extends SType {

	@NotNull
	@JsonProperty
	private Integer page_no;
	
	@NotNull
	@JsonProperty
	private Integer page_size;
	
	@JsonProperty
	private String user_state;
	
	@JsonProperty
	private String phone_number;
	
	@JsonProperty
	private String name_full;
	
	@JsonProperty
	private String user_role;
	
	@JsonProperty
	private String username;
	
}
