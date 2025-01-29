package aq.app.security.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuthenticationLoggingFilter extends OncePerRequestFilter {@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestId = request.getHeader("Request-Id");
		log.info("Successfully authenticated request with id " + requestId);
		filterChain.doFilter(request, response);
	}
}
