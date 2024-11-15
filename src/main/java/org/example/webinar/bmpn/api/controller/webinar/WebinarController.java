package org.example.webinar.bmpn.api.controller.webinar;
import org.example.webinar.bmpn.api.entity.Webinar;
import org.example.webinar.bmpn.api.model.WebinarDTO;
import org.example.webinar.bmpn.api.repository.WebinarRepository;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WebinarController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private WebinarService webinarService;

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
        return reservationService.addListener(processInstanceKey);
    }
}
