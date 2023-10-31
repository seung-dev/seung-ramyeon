package seung.boot.config.security.rest.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import seung.kimchi.types.SType;

@Accessors(fluent = true)
@Setter
@Getter
public class SSignupUsername extends SType {

	@Email
	@NotBlank
	@JsonProperty
	private String username;
	
//	@Pattern(regexp = "^(?!.* )(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "비밀번호는 8 ~ 16자 영문, 숫자, 특수문자를 사용하세요.")
	@NotBlank
	@JsonProperty
	private String secret;
	
	@JsonProperty
	private String group_code;
	
}
