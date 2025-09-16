package br.edu.utfpr.pb.tcc.server.validation;

import br.edu.utfpr.pb.tcc.server.annotation.UniqueCnpj;
import br.edu.utfpr.pb.tcc.server.repository.ClientSupplierRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UniqueCnpjValidator implements ConstraintValidator<UniqueCnpj, String>, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UniqueCnpjValidator.applicationContext = applicationContext;
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext constraintValidatorContext) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return true;
        }

        try {
            ClientSupplierRepository repository = applicationContext.getBean(ClientSupplierRepository.class);
            return repository.findByCnpj(cnpj) == null;
        } catch (Exception e) {
            return true;
        }
    }
}