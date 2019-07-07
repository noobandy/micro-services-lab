package in.anandm.msl.taskservice.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class EncryptionUtils {

	public static final String encryptPassword(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}

	public static final boolean checkPassword(String plainText, String hashed) {
		return BCrypt.checkpw(plainText, hashed);
	}

	public static final String signJWT(Map<String, Object> payload, String secret) {

		Instant iat = Instant.now();
		Instant exp = iat.plus(30, ChronoUnit.MINUTES);

		return Jwts.builder().setIssuedAt(new Date(iat.toEpochMilli())).setExpiration(new Date(exp.toEpochMilli()))
				.claim("payload", payload).signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public static final Claims validateJWT(String token, String secret) {
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		return claims;
	}

}
