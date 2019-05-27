package com.user.management.UserManagement.Security;


import com.user.management.UserManagement.Modals.JwtToken;
import com.user.management.UserManagement.Repositories.JwtTokenRepo;
import com.user.management.UserManagement.Utility.JwtTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.user.management.UserManagement.Security.SecurityConstants.*;

/**
 * created by Rahul
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    private JwtTokenHandler jwtTokenHandler;


    public JWTAuthorizationFilter(AuthenticationManager authManager, JwtTokenHandler jwtTokenHandler) {
        super(authManager);
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception failed){
            // reset the security context
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(req, res);
    }



    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = jwtTokenHandler.parseJwtToken(token);
            boolean isJwtTokenPresentInDb = jwtTokenHandler.isTokenPresentInDb(token);
                if (user != null && isJwtTokenPresentInDb) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }

            return null;
        }
        return null;
    }
}