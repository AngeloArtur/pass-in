package check.com.passin.domain.events.exceptions;

public class EventFullException extends RuntimeException {
    public EventFullException(String message) {
        super(message);
    }
}
