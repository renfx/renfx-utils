package com.rfxdevelop.utils.exception;

public class SecretException extends UtilException {
    public SecretException(){

    }
    public SecretException(String message) {
        super(message);
    }
    public SecretException(Throwable t){
        super(t);
    }
    public SecretException(String message, Throwable cause) {
        super(message, cause);
    }
}
