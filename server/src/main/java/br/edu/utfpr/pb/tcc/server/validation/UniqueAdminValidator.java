package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueAdmin;
import br.edu.utfpr.pb.tcc.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueAdminValidator implements ConstraintValidator<UniqueAdmin, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        if (role == null || !role.equals("ADMIN")) {
            return true;
        }
        boolean exists = userRepository.existsByRole("ADMIN");
        return !exists;
    }
}