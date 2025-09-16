package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueCpf;
import br.edu.utfpr.pb.tcc.server.repository.ClientSupplierRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String>, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UniqueCpfValidator.applicationContext = applicationContext;
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return true;
        }

        try {
            ClientSupplierRepository repository = applicationContext.getBean(ClientSupplierRepository.class);
            return repository.findByCpf(cpf) == null;
        } catch (Exception e) {
            return true;
        }
    }
}