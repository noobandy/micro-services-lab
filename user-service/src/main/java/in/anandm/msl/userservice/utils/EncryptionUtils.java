package in.anandm.msl.userservice.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import in.anandm.msl.userservice.model.UserDetails;
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

	public static final String signJWT(UserDetails userDetails, String secret) {
		Map<String, String> payload = new HashMap<>();
		payload.put("firstName", userDetails.getFirstName());
		payload.put("lastName", userDetails.getLastName());
		payload.put("email", userDetails.getEmail());
		
		Instant iat = Instant.now();
		Instant exp = iat.plus(30, ChronoUnit.MINUTES);
		

		return Jwts.builder().setSubject(userDetails.getEmail()).setIssuedAt(new Date(iat.toEpochMilli()))
				.setExpiration(new Date(exp.toEpochMilli())).claim("userDetails", payload)
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}
	

}
