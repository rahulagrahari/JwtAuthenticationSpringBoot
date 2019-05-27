package com.user.management.UserManagement.Security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.user.management.UserManagement.Modals.ApplicationUser;
import com.user.management.UserManagement.Modals.JwtToken;
import com.user.management.UserManagement.Repositories.JwtTokenRepo;
import com.user.management.UserManagement.Utility.JwtTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sun.security.krb5.internal.ccache.FileCredentialsCache;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.user.management.UserManagement.Security.SecurityConstants.*;

/**
 * created by Rahul
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


	private JwtTokenHandler jwtTokenHandler;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenHandler jwtTokenHandler) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenHandler = jwtTokenHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) {

		String token = jwtTokenHandler.generateJwtToken(auth);

		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

		jwtTokenHandler.saveTokenInDb(token);
	}
}