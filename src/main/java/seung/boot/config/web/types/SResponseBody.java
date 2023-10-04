package seung.boot.config.web.types;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.SConvert;
import seung.kimchi.types.SLinkedHashMap;
import seung.kimchi.types.SType;

@Builder(builderMethodName = "defaultBuilder")
@Accessors(fluent = true)
@Getter
public class SResponseBody extends SType {

	@JsonProperty
	private String trace_id;
	
	@Builder.Default
	@JsonProperty
	private long request_time = -1L;
	
	@Builder.Default
	@JsonProperty
	private long response_time = -1L;
	
	@Builder.Default
	@JsonProperty
	private long elapsed_time = -1L;
	
	@Builder.Default
	@Setter
	@JsonProperty
	private String error_code = SError.FAIL.code();
	
	@Builder.Default
	@Setter
	@JsonProperty
	private String error_message = "";
	
	@Builder.Default
	@JsonProperty
	private SLinkedHashMap data = new SLinkedHashMap();
	
	public static SResponseBodyBuilder builder(SRequestAttribute request_attribute) {
		return defaultBuilder()
				.trace_id(request_attribute.trace_id())
				.request_time(request_attribute.request_time())
				;
	}// end of builder
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	public SResponseBody add(Object key, Object value) {
		this.data.put(key, value);
		return this;
	}// end of add
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@JsonIgnore
	public SResponseBody merge(Map map) {
		if(map != null) {
			this.data.putAll(map);
		}
		return this;
	}// end of merge
	
	@JsonIgnore
	public SResponseBody parse(String json_format_string) throws JsonMappingException, JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		SLinkedHashMap response = new SLinkedHashMap(json_format_string);
		this.error_code = response.get_text("error_code");
		this.error_message = response.get_text("error_message");
		this.data = response.get_slinkedhashmap("data");
		return this;
	}// end of parse
	
	@JsonIgnore
	public void error(SError s_error) {
		this.error_code(s_error.code());
		this.error_message(s_error.message());
	}// end of error
	
	@JsonIgnore
	public void success() {
		this.error(SError.SUCCESS);
	}// end of success
	
	@JsonIgnore
	public void exception(Exception exception) {
		this.error_message(SConvert.throwable(exception));
	}// end of exception
	
	@JsonIgnore
	public boolean has_error() {
		return !SError.SUCCESS.code().equals(this.error_code);
	}// end of has_error
	
	@JsonIgnore
	public SResponseBody done() {
		this.response_time = System.currentTimeMillis();
		this.elapsed_time = response_time - request_time;
		return this;
	}// end of done
	
	@JsonIgnore
	public SResponseBody done(SError s_error) {
		this.error(s_error);
		return this.done();
	}// end of done
	
	@JsonIgnore
	public String get_text(String key, String default_value) {
		return this.data.get_text(key, default_value);
	}// end of get_text
	
	@JsonIgnore
	public Integer get_int(String key, Integer default_value) {
		return this.data.get_int(key, default_value);
	}// end of get_int
	
	@JsonIgnore
	public Long get_long(String key, Long default_value) {
		return this.data.get_long(key, default_value);
	}// end of get_long
	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public BigInteger get_bigint(String key, BigInteger default_value) {
		return this.data.get_bigint(key, default_value);
	}// end of get_bigint
	
	@JsonIgnore
	public Double get_double(String key, Double default_value) {
		return this.data.get_double(key, default_value);
	}// end of get_double
	
	@JsonIgnore
	public SLinkedHashMap get_slinkedhashmap(String key) throws IllegalArgumentException, IllegalAccessException {
		return this.data.get_slinkedhashmap(key);
	}// end of get_slinkedhashmap
	
	@JsonIgnore
	public List<SLinkedHashMap> get_list_slinkedhashmap(String key) {
		return this.data.get_list_slinkedhashmap(key);
	}// end of get_list_slinkedhashmap
	
	@JsonIgnore
	public String[] get_array_string(String key) {
		return this.data.get_array_string(key);
	}// end of get_array_string
	
	@JsonIgnore
	public List<String> get_list_string(String key) {
		return this.data.get_list_string(key);
	}// end of get_list_string
	
	@JsonIgnore
	public byte[] get_byte_array(String key) {
		return this.data.get_byte_array(key);
	}// end of get_byte_array
	
}
