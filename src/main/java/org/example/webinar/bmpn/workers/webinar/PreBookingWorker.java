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

        var preReservationData = PrereservationRequest.builder()
                .firstName(jobResultVariables.get("firstName").toString())
                .lastName(jobResultVariables.get("lastName").toString())
                .email(jobResultVariables.get("email").toString())
                .webinarId(Long.parseLong(jobResultVariables.get("webinarId").toString()))
                .build();

        var preReservationId = webinarService.preBookReservation(preReservationData);
        jobResultVariables.put("preReservationId", preReservationId);


        return jobResultVariables;
    }
}
