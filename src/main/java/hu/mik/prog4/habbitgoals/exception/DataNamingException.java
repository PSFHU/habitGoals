package hu.mik.prog4.habbitgoals.exception;

public class DataNamingException extends RuntimeException{
    public DataNamingException() {
    }

    public DataNamingException(String message) {
        super(message);
    }

    public DataNamingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNamingException(Throwable cause) {
        super(cause);
    }

    public DataNamingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
