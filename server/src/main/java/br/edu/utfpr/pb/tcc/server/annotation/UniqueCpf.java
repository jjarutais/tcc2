package br.edu.utfpr.pb.tcc.server.annotation;

import br.edu.utfpr.pb.tcc.server.validation.UniqueCpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCpfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {

    String message() default "{br.edu.pb.utfpr.tcc.server.client_supplier.cpf.Unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}