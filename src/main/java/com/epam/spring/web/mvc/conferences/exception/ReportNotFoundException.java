package com.epam.spring.web.mvc.conferences.exception;

import com.epam.spring.web.mvc.conferences.persistence.model.ErrorType;

public class ReportNotFoundException extends ServiceException {

  public ReportNotFoundException(String message) {
    super(message);
  }

  public ReportNotFoundException() {
    super("Report was not found");
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DB_ERROR_TYPE;
  }
}
