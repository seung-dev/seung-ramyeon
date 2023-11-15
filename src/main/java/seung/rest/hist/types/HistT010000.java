package seung.rest.hist.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class HistT010000 extends SType {

	@NotNull
	@JsonProperty
	private Integer page_no;
	
	@NotNull
	@JsonProperty
	private Integer page_size;
	
}
