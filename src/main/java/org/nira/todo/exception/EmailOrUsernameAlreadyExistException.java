package org.nira.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailOrUsernameAlreadyExistException extends RuntimeException {
    public EmailOrUsernameAlreadyExistException(String message) {
        super(message);
    }
}
