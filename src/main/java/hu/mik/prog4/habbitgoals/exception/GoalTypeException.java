package hu.mik.prog4.habbitgoals.exception;

public class GoalTypeException extends RuntimeException{
    public GoalTypeException(String message) {
        super(message);
    }

    public GoalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoalTypeException(Throwable cause) {
        super(cause);
    }

    public GoalTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
