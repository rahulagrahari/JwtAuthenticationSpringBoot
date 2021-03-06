package com.user.management.UserManagement.Security;

/**
 * created by yang on 12/02/2018
 */
public class SecurityConstants {
    public static final String SECRET = "JWTS4Adverts2@AdaptCentre";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}