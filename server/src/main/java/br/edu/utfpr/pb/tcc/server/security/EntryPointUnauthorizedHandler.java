package br.edu.utfpr.pb.tcc.server.security;

import br.edu.utfpr.pb.tcc.server.shared.GenericResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    private final MessageSource messageSource;

    public EntryPointUnauthorizedHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        String message;

        // Verifica se a exceção é uma DisabledException ou se a causa é uma DisabledException.
        if (authException instanceof DisabledException || (authException.getCause() instanceof DisabledException)) {
            message = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.notActive", null, LocaleContextHolder.getLocale());
        } else {
            message = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.authentication.badCredentials", null, LocaleContextHolder.getLocale());
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), GenericResponse.of(message));
    }
}