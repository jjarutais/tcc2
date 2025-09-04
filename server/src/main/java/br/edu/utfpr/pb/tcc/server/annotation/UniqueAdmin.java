package br.edu.utfpr.pb.tcc.server.annotation;

import br.edu.utfpr.pb.tcc.server.validation.UniqueAdminValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueAdminValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueAdmin {

    String message() default "{br.edu.pb.utfpr.tcc.server.user.singleAdmin.violation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}