package in.anandm.msl.taskservice.utils;

import java.util.Collections;
import java.util.Map;

public class AuthContext {

	private boolean authenticated;

	private Map<String, Object> payload;

	public AuthContext(boolean authenticated, Map<String, Object> payload) {
		super();
		this.authenticated = authenticated;
		this.payload = payload;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public Map<String, Object> getPayload() {
		return Collections.unmodifiableMap(payload);
	}

}
