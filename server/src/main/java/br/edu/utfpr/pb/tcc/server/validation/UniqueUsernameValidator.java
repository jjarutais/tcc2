package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueUsername;
import br.edu.utfpr.pb.tcc.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UniqueUsernameValidator.applicationContext = applicationContext;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null) {
            return true; // null values should be validated by @NotNull
        }
        
        try {
            UserRepository userRepository = applicationContext.getBean(UserRepository.class);
            return userRepository.findByUsername(username) == null;
        } catch (Exception e) {
            // Se não conseguir acessar o repository, retorna true para não bloquear a validação
            return true;
        }
    }
}
