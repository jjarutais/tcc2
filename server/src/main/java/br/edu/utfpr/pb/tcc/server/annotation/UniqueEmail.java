package br.edu.utfpr.pb.tcc.server.annotation;

import br.edu.utfpr.pb.tcc.server.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "{br.edu.pb.utfpr.tcc.server.user.email.Unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}