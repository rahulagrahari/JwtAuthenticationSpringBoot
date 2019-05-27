package com.user.management.UserManagement.Utility;

import com.user.management.UserManagement.Modals.JwtToken;
import com.user.management.UserManagement.Repositories.JwtTokenRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.user.management.UserManagement.Security.SecurityConstants.*;

@Component
public class JwtTokenHandler {

    @Autowired
    private JwtTokenRepo jwtTokenRepo;

    public String generateJwtToken(Authentication auth){
        String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

        return token;
    }

    public String parseJwtToken(String token){

        String user = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return user;
    }

    public boolean isTokenPresentInDb(String token){
        String tok = token.substring(7);
        Optional<JwtToken> jwtToken = jwtTokenRepo.findById(tok);
        return jwtToken.isPresent();
    }

    public void saveTokenInDb(String token){

        jwtTokenRepo.save(new JwtToken(token));
    }


}
