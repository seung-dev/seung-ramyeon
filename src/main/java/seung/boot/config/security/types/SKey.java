package seung.boot.config.security.types;

import java.security.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public class SKey {

	private final Key jwt_rsa_public_key;
	
	private final Key jwt_rsa_private_key;
	
}
