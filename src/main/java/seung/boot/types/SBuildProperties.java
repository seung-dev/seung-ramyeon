package seung.boot.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Builder
@Accessors(fluent = true)
@Getter
public class SBuildProperties extends SType {

	@JsonProperty
	private String build_group;
	
	@JsonProperty
	private String build_artifact;
	
	@JsonProperty
	private String build_name;
	
	@JsonProperty
	private String build_version;
	
	@JsonProperty
	private String build_time;
	
}
