package seung.boot.types;

import org.springframework.core.env.Environment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public class SEnvironment extends SType {

	// os
	@JsonProperty
	private final String os_name;
	
	@JsonProperty
	private final boolean is_windows;
	
	@JsonProperty
	private final boolean is_linux;
	
	@JsonProperty
	private final boolean is_mac;
	
	@JsonProperty
	private final String os_ver;
	
	// mode
	@JsonProperty
	private final String app_mode;
	
	@JsonProperty
	private final boolean is_ops;
	
	@JsonProperty
	private final boolean is_dev;
	
	@JsonProperty
	private final boolean is_loc;
	
	// server
	@JsonProperty
	private final String host_name;
	
	// app
	@JsonProperty
	private final String app_name;
	
	@JsonProperty
	private final String app_port;
	
	// nas
	@JsonProperty
	private final String nas_path;
	
	// shutdown
	@JsonProperty
	private boolean shutdown;
	
	@JsonProperty
	private String shutdown_message = "";
	
	public SEnvironment(
			String os_name
			, String os_ver
			, String app_mode
			, String host_name
			, String app_name
			, String app_port
			, String nas_path_windows
			, String nas_path_linux
			, String nas_path_mac
			) {
		this(os_name
				, os_name.toLowerCase().contains("win")//is_windows
				, os_name.toLowerCase().contains("linux")//is_linux
				, os_name.toLowerCase().contains("mac")//is_mac
				, os_ver
				, app_mode
				, "ops".equals(app_mode)//is_ops
				, "dev".equals(app_mode)//is_dev
				, "loc".equals(app_mode)//is_loc
				, host_name
				, app_name
				, app_port
				, os_name.toLowerCase().contains("win") ? nas_path_windows
						: os_name.toLowerCase().contains("linux") ? nas_path_linux
						: os_name.toLowerCase().contains("mac") ? nas_path_mac
						: ""//nas_path
				, false//shutdown
				, ""//shutdown_message
				);
	}
	
	public SEnvironment(
			Environment environment
			) {
		this(environment.getProperty("os.name", "")//os_name
				, environment.getProperty("os.version", "")//os_ver
				, environment.getProperty("spring.profiles.active", "")//app_mode
				, environment.getProperty("server.hostname", "")//host_name
				, environment.getProperty("spring.application.name", "")//app_name
				, environment.getProperty("server.port")//app_port
				, environment.getProperty(SPropertyNames._S_NAS_PATH_WINDOWS)//nas_path_windows
				, environment.getProperty(SPropertyNames._S_NAS_PATH_LINUX)//nas_path_linux
				, environment.getProperty(SPropertyNames._S_NAS_PATH_MAC)//nas_path_mac
				);
	}
	
	public void shutdown(boolean shutdown, String shutdown_message) {
		this.shutdown = shutdown;
		this.shutdown_message = shutdown_message;
	}// end of shutdown
	
}
