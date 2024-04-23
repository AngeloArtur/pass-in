package check.com.passin.controllers;

import check.com.passin.dto.attendee.AttendeeIdDTO;
import check.com.passin.dto.attendee.AttendeeListResponseDTO;
import check.com.passin.dto.attendee.AttendeeRequestDTO;
import check.com.passin.dto.event.EventIdDTO;
import check.com.passin.dto.event.EventRequestDTO;
import check.com.passin.dto.event.EventResponseDTO;
import check.com.passin.services.AttendeeService;
import check.com.passin.services.EventService;
import com.sun.java.accessibility.util.EventID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.eventService.getEventDetails(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO  attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriComponentsBuilder.path("/attendees/{attendId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeeListResponseDTO attendeeListResponse = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeeListResponse);
    }
}
