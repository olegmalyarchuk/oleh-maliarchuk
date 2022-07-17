package com.epam.spring.web.mvc.conferences.exception;

import com.epam.spring.web.mvc.conferences.persistence.model.ErrorType;

public class UserNotFoundException extends ServiceException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException() {
    super("User was not found");
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DB_ERROR_TYPE;
  }
}
