package com.truby.cst8277.exception;

public class CantFollow extends RuntimeException {
    public CantFollow() {
        super(MessageProm.CANT_FOLLOW_YOURSELF);
    }
}
