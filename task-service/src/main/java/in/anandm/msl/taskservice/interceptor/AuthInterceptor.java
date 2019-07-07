package in.anandm.msl.taskservice.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.anandm.msl.taskservice.utils.AuthContext;
import in.anandm.msl.taskservice.utils.AuthContextHolder;
import in.anandm.msl.taskservice.utils.EncryptionUtils;
import io.jsonwebtoken.Claims;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Value("${jwt.secret}")
	private String jwtSecret;

	private String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = getTokenFromRequest(request);
		Claims claims = EncryptionUtils.validateJWT(token, jwtSecret);
		AuthContext context = new AuthContext(true, (Map<String, Object>) claims.get("payload"));
		AuthContextHolder.setAuthContext(context);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		AuthContextHolder.unsetAuthContext();
	}

}
