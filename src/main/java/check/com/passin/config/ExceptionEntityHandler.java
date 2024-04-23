package check.com.passin.config;

import check.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import check.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import check.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import check.com.passin.domain.events.Event;
import check.com.passin.domain.events.exceptions.EventFullException;
import check.com.passin.domain.events.exceptions.EventNotFoundException;
import check.com.passin.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handlerEventNotFound(EventNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handlerEventFull(EventFullException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handlerAttendeeNotFound(AttendeeNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handlerAttendeeAlreadyExists(AttendeeAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handlerCheckInAlreadyExists(CheckInAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
