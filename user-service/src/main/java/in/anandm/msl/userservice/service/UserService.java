package in.anandm.msl.userservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.anandm.msl.userservice.model.UserDetails;
import in.anandm.msl.userservice.model.UserRepository;
import in.anandm.msl.userservice.utils.EncryptionUtils;
import in.anandm.msl.userservice.utils.ValidationUtils;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	private Map<String, String> validateUserDetails(UserDetails userDetails) {
		Map<String, String> validationErrors = new HashMap<>();
		if (ValidationUtils.isFilled(userDetails.getFirstName())) {
			if (!ValidationUtils.onlyApha(userDetails.getFirstName())) {
				validationErrors.put("firstName", "register.firstName.invalid");
			}
		}

		if (ValidationUtils.isFilled(userDetails.getLastName())) {
			if (!ValidationUtils.onlyApha(userDetails.getFirstName())) {
				validationErrors.put("lastName", "register.lastName.invalid");
			}
		}

		if (!ValidationUtils.isValidEmail(userDetails.getEmail())) {
			validationErrors.put("email", "register.email.invalid");
		} else {
			UserDetails found = userRepository.findUserByEmail(userDetails.getEmail());
			if (found != null) {
				validationErrors.put("email", "register.email.taken");
			}
		}

		if (!ValidationUtils.isValidPassword(userDetails.getPassword())) {
			validationErrors.put("password", "register.password.invalid");
		}

		return validationErrors;
	}

	public Map<String, String> registerUser(UserDetails userDetails) {
		Map<String, String> validationErrors = validateUserDetails(userDetails);

		if (validationErrors.isEmpty()) {
			userDetails.setPassword(EncryptionUtils.encryptPassword(userDetails.getPassword()));
			userRepository.save(userDetails);
		}

		return validationErrors;
	}
	
	public UserDetails validateUser(String email, String password) {
		UserDetails found = userRepository.findUserByEmail(email);
		
		if(found != null) {
			boolean passwordMatched = EncryptionUtils.checkPassword(password, found.getPassword());
			if(!passwordMatched || !found.isEnabled()) {
				found = null;
			}
		}
		
		
		return found;
	}
}
