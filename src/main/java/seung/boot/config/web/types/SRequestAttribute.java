package seung.boot.config.web.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Builder(builderMethodName = "defaultBuilder")
@Accessors(fluent = true)
@Getter
public class SRequestAttribute extends SType {

	@JsonProperty
	private String trace_id;
	
	@NotBlank
	@JsonProperty
	private String local_addr;
	
	@NotBlank
	@JsonProperty
	private String local_name;
	
	@NotNull
	@JsonProperty
	private Integer local_port;
	
	@Builder.Default
	@JsonProperty
	private long request_time = System.currentTimeMillis();
	
	@Setter
	@JsonProperty
	private long response_time;
	
	@JsonProperty
	private String http_method;
	
	@JsonProperty
	private String scheme;
	
	@JsonProperty
	private String host;
	
	@JsonProperty
	private int port;
	
	@JsonProperty
	private String url_path;
	
	@JsonProperty
	private String url_query;
	
	@JsonProperty
	private String remote_addr;
	
	@JsonProperty
	private String content_type;
	
	@JsonProperty
	private String user_agent;
	
	@JsonProperty
	private String referer;
	
	@Setter
	@JsonProperty
	private String principal;
	
	@Setter
	@JsonProperty
	private String error_code;
	
	@Builder.Default
	@JsonProperty
	private List<String> roles = new ArrayList<>();
	
	public static SRequestAttributeBuilder builder(final HttpServletRequest request) {
		long request_time = System.currentTimeMillis();
		return defaultBuilder()
				.trace_id(String.format("%d%s", request_time, RandomStringUtils.random(7, true, true)))
				.local_addr(request.getLocalAddr())
				.local_name(request.getLocalName())
				.local_port(request.getLocalPort())
				.http_method(request.getMethod())
				.scheme(SRequest.scheme(request))
				.host(SRequest.host(request))
				.port(request.getServerPort())
				.url_path(request.getRequestURI())
				.url_query(request.getQueryString())
				.remote_addr(SRequest.remote_addr(request))
				.content_type(request.getContentType())
				.user_agent(request.getHeader("user-agent"))
				;
	}
	
	public SRequestAttribute role(String role) {
		this.roles.add(role);
		return this;
	}// end of role
	
	public void roles(ArrayList<String> roles) {
		this.roles.addAll(roles);
	}// end of roles
	
	public boolean has_role(String role) {
		return this.roles.contains(role);
	}// end of has_role
	
}
