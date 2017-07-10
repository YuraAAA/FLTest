package com.dev24.flicker.core.fsm;

public class SwitcherException extends RuntimeException {

    public SwitcherException() {
    }

    public SwitcherException(String detailMessage) {
        super(detailMessage);
    }

    public SwitcherException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SwitcherException(Throwable throwable) {
        super(throwable);
    }
}
