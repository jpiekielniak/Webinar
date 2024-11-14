package org.example.webinar.bmpn.workers.webinar;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.AllArgsConstructor;
import org.example.webinar.bmpn.api.model.request.PrereservationRequest;
import org.example.webinar.bmpn.api.service.webinar.WebinarService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PreBookingWorker {

    private WebinarService webinarService;

    @JobWorker(type = "preBooking")
    public Map<String, Object> preBooking(final JobClient client, final ActivatedJob job) {
        var jobResultVariables = job.getVariablesAsMap();

        var reservationData = PrereservationRequest.builder()
                .firstName((String) jobResultVariables.get("firstName"))
                .lastName((String) jobResultVariables.get("lastName"))
                .email((String) jobResultVariables.get("email"))
                .webinarId((Long) jobResultVariables.get("webinarId"))
                .build();

        var reservationId = webinarService.preBookReservation(reservationData);
        jobResultVariables.put("preReservationId", reservationId);


        return jobResultVariables;
    }
}
