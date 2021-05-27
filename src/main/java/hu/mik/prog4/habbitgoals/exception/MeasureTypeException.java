package hu.mik.prog4.habbitgoals.exception;

public class MeasureTypeException extends RuntimeException{
    public MeasureTypeException(String message) {
        super(message);
    }

    public MeasureTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeasureTypeException(Throwable cause) {
        super(cause);
    }

    public MeasureTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
