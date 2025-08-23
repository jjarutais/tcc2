package br.edu.utfpr.pb.tcc.server.security;

public class SecurityConstants {
    public static final String SECRET = "utfpr@2025"; // secret do token
    public static final long EXPIRATION_TIME = 86400000; // 1 dia
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}