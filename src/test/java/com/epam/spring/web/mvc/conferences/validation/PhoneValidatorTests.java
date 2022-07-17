package com.epam.spring.web.mvc.conferences.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class PhoneValidatorTests {

  @InjectMocks private PhoneValidator phoneValidator;

  @Mock private ConstraintValidatorContext context;

  @Test
  void givenValidPhone() {
    String phone = "+123456789";

    boolean isValidResult = phoneValidator.isValid(phone, context);
    assertTrue(isValidResult);
  }

  @Test
  void givenNotValidPhone() {
    String phone = "+1234567890";

    boolean isValidResult = phoneValidator.isValid(phone, context);
    assertFalse(isValidResult);
  }

  @Test
  void givenEmptyPhone() {
    String phone = "";

    boolean isValidResult = phoneValidator.isValid(phone, context);
    assertFalse(isValidResult);
  }
}
