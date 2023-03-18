package com.truby.cst8277.exception;

public class NotFoundUserName extends RuntimeException {
    public NotFoundUserName() {
        super(MessageProm.NOT_FOUND_USERNAME);
    }
}
