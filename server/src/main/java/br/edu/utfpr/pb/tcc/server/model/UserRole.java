package br.edu.utfpr.pb.tcc.server.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name(); // "ROLE_ADMIN" ou "ROLE_USER"
    }
}
