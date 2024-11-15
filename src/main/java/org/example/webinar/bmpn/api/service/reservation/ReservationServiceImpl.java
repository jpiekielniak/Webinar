package org.example.webinar.bmpn.api.service.reservation;

import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final Map<String, SseEmitter> listeners = new ConcurrentHashMap<>();

    @Override
    public Optional<Long> addReservation(Reservation reservation) {
        return Optional.of(1L);
    }

    @Override
    public void changeReservationStatus(Long reservationId, ReservationStatus newStatus) {
    }

    @Override
    public void deleteReservation(Long reservationId) {

    }

    public SseEmitter addListener(String listenerId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        listeners.put(listenerId, emitter);

        emitter.onCompletion(() -> listeners.remove(listenerId));
        emitter.onTimeout(() -> listeners.remove(listenerId));
        emitter.onError((e) -> listeners.remove(listenerId));

        return emitter;
    }

    public void sendMessageToListener(String listenerId, String message) {
        SseEmitter emitter = listeners.get(listenerId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                listeners.remove(listenerId);
            }
        }
    }

    @Override
    public void removeListener(String listenerId) {
        this.listeners.remove(listenerId);
    }
}
