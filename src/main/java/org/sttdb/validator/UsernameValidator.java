package org.sttdb.validator;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sttdb.services.UserService;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    @Inject
    UserService userService;

    @Override
    public void initialize(Username constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isUsernameExist(s);
    }
}
