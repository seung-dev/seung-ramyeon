package seung.boot.config.security.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import seung.boot.config.security.types.SUser;
import seung.boot.config.web.types.SError;
import seung.kimchi.SConvert;

@Slf4j
public class SJWT {

	public final static String _S_ROLES = "s-roles";
	
	public static SecretKey hmac_key(final String hex_key) throws WeakKeyException, DecoderException {
		return Keys.hmacShaKeyFor(Hex.decodeHex(hex_key));
	}// end of hmac_key
	
	public static SecretKey rsa_private_key(final String private_key) throws WeakKeyException, DecoderException {
		return new SecretKeySpec(SConvert.decode_hex(private_key), SignatureAlgorithm.RS256.getJcaName());
	}// end of rsa_private_key
	
	public static String generate_token(
			final Key key
			, final String iss
			, final long duration
			, final String subject
			, final Map<String, Object> claims
			) {
		
		final long iat = System.currentTimeMillis() / 1000;
		final long exp = iat + duration;
		
		JwtBuilder jwtBuilder = Jwts.builder()
				.signWith(key, SignatureAlgorithm.RS256)
				.setHeaderParam(JwsHeader.TYPE, Header.JWT_TYPE)
//				.setHeaderParam(JwsHeader.ALGORITHM, s_jwt.getAlg())
//				.setHeaderParam(JwsHeader.KEY_ID, s_jwt.getKid())
				.claim(Claims.ISSUER, iss)
//				.claim(Claims.ID, s_jwt.getJti())
				.claim(Claims.SUBJECT, subject)
//				.claim(Claims.AUDIENCE, s_jwt.getAud())
				.claim(Claims.ISSUED_AT, iat)
//				.claim(Claims.NOT_BEFORE, current_time)
				.claim(Claims.EXPIRATION, exp)
				;
		
		if(claims != null) {
			jwtBuilder = jwtBuilder.addClaims(claims);
		}
		
		return jwtBuilder.compact();
	}// end of generate_token
	
	public static String generate_token(
			final Key key
			, final String iss
			, final long duration
			, final SUser s_user
			) {
		return generate_token(
				key
				, iss
				, duration
				, s_user.getName()//subject
				, Collections.singletonMap(_S_ROLES, s_user.roles())//claims
				);
	}// end of generate_token
	
	public static String generate_token(
			final Key key
			, final String iss
			, final long duration
			, final Authentication authentication
			) {
		return generate_token(
				key
				, iss
				, duration
				, (SUser) authentication.getPrincipal()//s_user
				);
	}// end of generate_token
	
	public static String generate_cookie(
			final String cookie_name
			, final String token_type
			, final String token
			, final long max_age
			, final String cookie_path
			, final SameSite same_site
			, final boolean http_only
			, final boolean secure
			) throws UnsupportedEncodingException {
		final String cookie_value = token_type != null && token_type.length() > 0 ? String.join(" ", token_type, token) : token;
		return ResponseCookie.from(
				cookie_name//name
				, URLEncoder.encode(
						cookie_value
						, StandardCharsets.UTF_8.name()
						)
				)//value
				.maxAge(max_age)
				.path(cookie_path)
				.sameSite(same_site.name())
				.httpOnly(http_only)
				.secure(secure)
				.build()
				.toString();
	}// end of generate_cookie
	
	public static String generate_cookie(
			final String cookie_name
			, final String token_type
			, final String token
			, final long max_age
			, final String cookie_path
			) throws UnsupportedEncodingException {
		return generate_cookie(
				cookie_name
				, token_type
				, token
				, max_age
				, cookie_path
				, SameSite.STRICT//same_site
				, true//http_only
				, true//secure
				);
	}// end of generate_cookie
	
	public static String generate_cookie_clear(
			final String cookie_name
			, final String cookie_path
			, final SameSite same_site
			, final boolean http_only
			, final boolean secure
			) throws UnsupportedEncodingException {
		return generate_cookie(
				cookie_name
				, ""//token_type
				, ""//token
				, 0//max_age
				, cookie_path
				, same_site
				, http_only
				, secure
				);
	}// end of generate_cookie
	
	public static String generate_cookie_clear(
			final String cookie_name
			, final String cookie_path
			) throws UnsupportedEncodingException {
		return generate_cookie(
				cookie_name
				, ""//token_type
				, ""//token
				, 0//max_age
				, cookie_path
				, SameSite.STRICT//same_site
				, true//http_only
				, true//secure
				);
	}// end of generate_cookie
	
	public static Cookie cookie(
			final HttpServletRequest request
			, final String cookie_name
			) {
		return WebUtils.getCookie(request, cookie_name);
	}// end of cookie
	
	public static String token(
			final Cookie cookie
			, final String token_type
			) throws UnsupportedEncodingException {
		if(cookie == null) {
			return null;
		}
		String token = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.name());
		if(token == null) {
			return null;
		}
		if(token_type != null) {
			if(!token.startsWith(token_type)) {
				return null;
			}
		}
		return token.substring(token_type.length()).trim();
	}// end of token
	
	public static String token(
			final HttpServletRequest request
			, final String cookie_name
			, final String token_type
			) throws UnsupportedEncodingException {
		return token(cookie(request, cookie_name), token_type);
	}// end of token
	
	public static SError verify(
			final Key key
			, final String token
			) {
		SError s_error = SError.TOKEN_IS_INVALID;
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			s_error = SError.TOKEN_IS_VALID;
		} catch (SecurityException e) {
			log.debug("exception=", e);
			s_error = SError.TOKEN_IS_NOT_SECURE;
		} catch (MalformedJwtException e) {
			log.debug("exception=", e);
			s_error = SError.TOKEN_IS_MALFORMED;
		} catch (ExpiredJwtException e) {
			log.debug("exception=", e);
			s_error = SError.TOKEN_IS_EXPIERED;
		} catch (UnsupportedJwtException e) {
			log.debug("exception=", e);
			s_error = SError.TOKEN_IS_UNSUPPORTED;
		} catch (IllegalArgumentException e) {
			log.debug("exception=", e);
			s_error = SError.TOKEN_IS_INVALID;
		}// end of try
		return s_error;
	}// end of verify
	
	public static Claims claims(
			final Key key
			, final String token
			) {
		if(SError.TOKEN_IS_VALID != verify(key, token)) {
			return null;
		}
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}// end of claims
	
	public static Authentication authentication(final Key key, final String token) {
		final Claims claims = claims(key, token);
		if(claims == null) {
			return null;
		}
		final SUser s_user = new SUser(
				"app"//provider
				, claims.getSubject()
				, claims.get(_S_ROLES).toString()
				);
		return new UsernamePasswordAuthenticationToken(
				s_user//principal
				, ""//credentials
				, s_user.getAuthorities()//authorities
				);
	}// end of authentication
	
}
