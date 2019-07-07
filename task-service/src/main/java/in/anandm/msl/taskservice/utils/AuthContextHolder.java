package in.anandm.msl.taskservice.utils;

public class AuthContextHolder {

	private static final ThreadLocal<AuthContext> THREAD_LOCAL = new ThreadLocal<>();

	public static final void setAuthContext(AuthContext authContext) {
		THREAD_LOCAL.set(authContext);
	}

	public static final void unsetAuthContext() {
		THREAD_LOCAL.set(null);
	}

	public static final AuthContext getAuthContext() {
		return THREAD_LOCAL.get();
	}
}
