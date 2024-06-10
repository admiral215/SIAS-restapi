package org.sttdb.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernameValidator.class)
public @interface Username {
    String message() default "Username already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
