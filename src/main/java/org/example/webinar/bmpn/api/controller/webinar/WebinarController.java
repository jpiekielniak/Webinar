package org.example.webinar.bmpn.api.controller.webinar;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebinarController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/webinars")
    public ResponseEntity<List<String>> getMyOrders() {
        List<String> webinars = List.of(
                "Programowanie",
                "Nowoczesny HTML",
                "Docker w praktyce",
                "Azure od postaw",
                "Mikroserwisy w .NET",
                "Databricks - wprowadzenie",
                "Relacyjne bazy danych"
        );
        return ResponseEntity.ok(webinars);
    }

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam String processInstanceKey) {
        return reservationService.addListener(processInstanceKey);
    }
}
