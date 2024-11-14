package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.entity.Reservation;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PreBookingWorker {

    private WebinarService webinarService;
    private ReservationService reservationService;

    @JobWorker(type = "preBooking")
    public Map<String, Object> preBooking(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var webinarId = Long.parseLong(jobResultVariables.get("webinarId").toString());
        final var webinar = webinarService
                .getById(webinarId);

        final var reservation = Reservation.builder()
                .firstName(jobResultVariables.get("firstName").toString())
                .lastName(jobResultVariables.get("lastName").toString())
                .email(jobResultVariables.get("email").toString())
                .webinar(webinar.get())
                .build();

        final var reservationId = reservationService
                .addReservation(reservation);

        if(reservationId.isEmpty())
        {
            client.newThrowErrorCommand(job.getKey())
                    .errorCode("RESERVATION_NOT_CREATED")
                    .send()
                    .join();
        }

        jobResultVariables.put("reservationId", reservationId);
        jobResultVariables.put("processInstanceKey", job.getProcessInstanceKey());

        return jobResultVariables;
    }
}
