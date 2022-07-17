package com.epam.spring.web.mvc.conferences.exception;

import com.epam.spring.web.mvc.conferences.persistence.model.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class ServiceException extends RuntimeException {
  private ErrorType errorType;

  public ServiceException(String message) {
    super(message);
  }

  public ErrorType getErrorType() {
    return ErrorType.FATAL_ERROR_TYPE;
  }
}
