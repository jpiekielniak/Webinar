package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class RemovePreBookingWorker {

    private ReservationService reservationService;

    @JobWorker(type = "removePreBooking")
    public Map<String, Object> removePreBooking(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var reservationId = Long.parseLong(jobResultVariables.get("reservationId").toString());
        reservationService.deleteReservation(reservationId);

        return jobResultVariables;
    }
}
