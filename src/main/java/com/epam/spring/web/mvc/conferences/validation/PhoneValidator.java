package com.epam.spring.web.mvc.conferences.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_REGEX = "^\\+\\d{9}$";

    @Override
    public void initialize(Phone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isBlank()) {
            return false;
        }
        return s.matches(PHONE_REGEX);
    }
}
