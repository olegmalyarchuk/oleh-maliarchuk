package com.epam.spring.web.mvc.conferences.exception;

import com.epam.spring.web.mvc.conferences.persistence.model.ErrorType;

public class EventNotFoundException extends ServiceException{

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException() {
        super("Event was not found");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DB_ERROR_TYPE;
    }

}
