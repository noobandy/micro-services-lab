package in.anandm.msl.userservice.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final Pattern ONLY_CHAR_PATTERN = Pattern.compile("\\w+\\.?");
	private static final String PASSWORD_PATTERN = "";
	
	public static final boolean isFilled(String input) {
		return input != null && !input.trim().isEmpty();
	}
	
	public static final boolean onlyApha(String input) {
		return isFilled(input) && ONLY_CHAR_PATTERN.matcher(input).matches();
	}
	
	public static final boolean isValidPassword(String input) {
		return isFilled(input); // && input.matches(PASSWORD_PATTERN);
	}
	
	public static final boolean isValidEmail(String input) {
		return isFilled(input) && VALID_EMAIL_ADDRESS_REGEX.matcher(input).matches();
	}
}
