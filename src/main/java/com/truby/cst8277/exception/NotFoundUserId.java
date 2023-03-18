package com.truby.cst8277.exception;

public class NotFoundUserId extends RuntimeException {
    public NotFoundUserId() {
        super("USER " + MessageProm.NOT_FOUND_ID);
    }
}
