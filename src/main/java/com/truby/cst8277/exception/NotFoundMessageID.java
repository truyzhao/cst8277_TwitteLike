package com.truby.cst8277.exception;

public class NotFoundMessageID extends RuntimeException {
    public NotFoundMessageID() {
        super("TWEET " + MessageProm.NOT_FOUND_ID);
    }
}
