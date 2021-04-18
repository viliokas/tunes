package com.example.tunes.exception;

public class NotRespondingException extends RuntimeException {
    public NotRespondingException(String server) { super(server +" server " + " not responding"); }
}

