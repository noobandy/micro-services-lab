package in.anandm.msl.userservice.api.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.anandm.msl.userservice.model.UserDetails;
import in.anandm.msl.userservice.service.UserService;
import in.anandm.msl.userservice.utils.EncryptionUtils;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Value(value = "${jwt.secret}")
	private String jwtSecret;

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody UserDetails userDetails) {
		Map<String, Object> result = new HashMap<>();

		Map<String, String> errrors = userService.registerUser(userDetails);

		if (!errrors.isEmpty()) {
			result.put("errors", errrors);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> authenticate(@RequestBody UserDetails userDetails) {

		UserDetails found = userService.validateUser(userDetails.getEmail(), userDetails.getPassword());

		if (found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("accessToken", EncryptionUtils.signJWT(found, jwtSecret));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
