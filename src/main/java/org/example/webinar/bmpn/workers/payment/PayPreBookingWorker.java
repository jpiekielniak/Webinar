package org.example.webinar.bmpn.workers.payment;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.entity.ReservationStatus;
import org.example.webinar.bmpn.api.service.reservation.ReservationService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PayPreBookingWorker {

    private ReservationService reservationService;

    @JobWorker(type = "payPreBooking")
    public Map<String, Object> payPreBooking(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        final var reservationId = Long.parseLong(jobResultVariables.get("reservationId").toString());
        reservationService.changeReservationStatus(reservationId, ReservationStatus.PAYED);

        return jobResultVariables;
    }
}
