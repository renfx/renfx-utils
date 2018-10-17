package com.rfxdevelop.utils.exception;

public class UtilException extends RuntimeException {
    public UtilException(){

    }
    public UtilException(String message) {
        super(message);
    }
    public UtilException(Throwable t){
        super(t);
    }
    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
