package org.example.webinar.bmpn.api.controller.webinar;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.model.ParticipantInfo;
import org.example.webinar.bmpn.api.model.WebinarDTO;
import org.example.webinar.bmpn.api.service.email.EmailService;
import org.example.webinar.bmpn.api.service.emitter.EmitterService;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebinarController {
    private ReservationService reservationService;

    private WebinarService webinarService;
    private EmitterService emitterService;

    @GetMapping("/webinars")
    public ResponseEntity<List<WebinarDTO>> getMyOrders() {
        List<WebinarDTO> webinars = webinarService.getWebinarList()
                .stream()
                .map(webinar -> new WebinarDTO(webinar.getTitle(), webinar.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(webinars);
    }

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam String processInstanceKey) {
        return emitterService.addListener(processInstanceKey);
    }

    @GetMapping("webinars/{selectedWebinarId}/participants")
    public ResponseEntity<List<ParticipantInfo>> addParticipantToWebinar(@PathVariable Long selectedWebinarId) {
        return ResponseEntity.ok(webinarService.getWebinarParticipants(selectedWebinarId));
    }
}
