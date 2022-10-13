package com.gfl.resources_server.exception;

import org.springframework.http.HttpStatus;

public class RequestAuthorizationException extends RuntimeException {
    private HttpStatus httpStatus;

    public RequestAuthorizationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public RequestAuthorizationException(String msg) {
        super(msg);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
