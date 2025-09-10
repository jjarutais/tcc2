package br.edu.utfpr.pb.tcc.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.username.NotNull}")
    @Size(min = 4, max = 255, message = "{br.edu.pb.utfpr.tcc.server.user.username.Size}")
    private String username;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.displayName.NotNull}")
    @Size(min = 4, max = 255, message = "{br.edu.pb.utfpr.tcc.server.user.displayName.Size}")
    private String displayName;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.password.NotNull}")
    @Size(min = 8, message = "{br.edu.pb.utfpr.tcc.server.user.password.Size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{br.edu.pb.utfpr.tcc.server.user.password.Pattern}")
    private String password;

    @Column(unique = true)
    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.email.NotNull}")
    @Email(message = "{br.edu.pb.utfpr.tcc.server.user.email.Valid}")
    private String email;

    private String role;

    private boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return Collections.emptyList();
        }
        return AuthorityUtils.createAuthorityList("ROLE_" + role.toUpperCase());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}