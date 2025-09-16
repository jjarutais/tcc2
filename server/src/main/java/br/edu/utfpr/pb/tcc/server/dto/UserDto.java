package br.edu.utfpr.pb.tcc.server.dto;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueAdmin;
import br.edu.utfpr.pb.tcc.server.annotation.UniqueEmail;
import br.edu.utfpr.pb.tcc.server.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @UniqueUsername
    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.username.NotNull}")
    @Size(min = 4, max = 255, message = "{br.edu.pb.utfpr.tcc.server.user.username.Size}")
    private String username;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.displayName.NotNull}")
    @Size(min = 4, max = 255, message = "{br.edu.pb.utfpr.tcc.server.user.displayName.Size}")
    private String displayName;

    @UniqueEmail
    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.email.NotNull}")
    @Email(message = "{br.edu.pb.utfpr.tcc.server.user.email.Valid}")
    private String email;

    @NotNull(message = "{br.edu.pb.utfpr.tcc.server.user.password.NotNull}")
    @Size(min = 8, message = "{br.edu.pb.utfpr.tcc.server.user.password.Size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{br.edu.pb.utfpr.tcc.server.user.password.Pattern}")
    private String password;

    @UniqueAdmin
    private String role;

    private boolean active;
}