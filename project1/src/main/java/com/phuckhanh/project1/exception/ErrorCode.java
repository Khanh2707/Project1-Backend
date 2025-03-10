package com.phuckhanh.project1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UN_AUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ACCOUNT_EXISTED(1002, "Account already exists", HttpStatus.CONFLICT),
    ACCOUNT_NOTFOUND(1003, "Account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_USERNAME_NOT_NULL(1004, "Account username is null", HttpStatus.BAD_REQUEST),
    ACCOUNT_PASSWORD_NOT_NULL(1005, "Account password is null", HttpStatus.BAD_REQUEST),
    ACCOUNT_AGE_NOT_SMALLER_THAN_1(1006, "Account age is not smaller than 1", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
